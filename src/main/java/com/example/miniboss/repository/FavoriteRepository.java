package com.example.miniboss.repository;

import com.example.miniboss.exception.FavoriteNotFoundException;
import com.example.miniboss.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class FavoriteRepository {
    @Autowired
    private JdbcTemplate jtm;
    @Autowired
    private FactRepository factRepository;

    private Integer userId = 1;
    public static final int MYSQL_DUPLICATE_PK = 1062;

    public Optional<Favorite> findAllById(Integer id) {
        Favorite favorite = new Favorite();
        try {

            String sql = "SELECT id, fact_id, text, created_by, created_datetime, updated_by, updated_datetime FROM tbl_favorite_fact where id = ? and user_id = ?";

            favorite = jtm.queryForObject(sql, new Object[]{id, userId},
                    new BeanPropertyRowMapper<>(Favorite.class));
            return Optional.of(favorite);
        }
        catch (Exception e) {
            System.out.println(e);
            return Optional.of(favorite);
        }
    }

    public Optional<List> findAll() {
        String sql = "SELECT id, user_id, fact_id, text, created_by, created_datetime, updated_by, updated_datetime FROM tbl_favorite_fact where user_id = ?";

        List<Favorite> favorite = jtm.query(sql, new Object[]{userId},
                new BeanPropertyRowMapper<>(Favorite.class));
        return Optional.of(favorite);
    }

    public Favorite save(Favorite favorite) throws Exception{
        try {
            String sql = "INSERT INTO tbl_favorite_fact (user_id, fact_id, text, created_by, created_datetime, updated_by, updated_datetime) VALUES\n" +
                    "  (?, ?, ?, ?, ?, ?, ? )";
            favorite.setText(factRepository.findAllById(favorite.getFactId()).orElseThrow().getText());
            favorite.setUserId(userId);
            favorite.setCreatedBy("SYSTEM");
            favorite.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));

            int a = jtm.update(sql, favorite.getUserId(), favorite.getFactId(), favorite.getText(), favorite.getCreatedBy(), favorite.getCreatedDatetime(), null, null);

            if (a > 0) {
                favorite.setId(a);
                return favorite;
            } else {
                throw new Exception();
            }
        }
        catch(SQLException e) {
            if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
                //duplicate primary key
                throw new FavoriteNotFoundException("Cannot insert duplicate favorite");
            }
            else throw new Exception();
        }
        catch (DuplicateKeyException e){
            throw new FavoriteNotFoundException("Cannot insert duplicate favorite");
        }
        catch (Exception e) {
            System.out.println(e);
            throw new Exception();
        }
    }

    public Integer deleteById(Integer id) throws Exception {
        Favorite favorite = new Favorite();
        try {
            String sql = "DELETE FROM tbl_favorite_fact WHERE id = ? and user_id = ?";
            int result = jtm.update(sql, new Object[]{id, userId});
            if (result > 0) {
                return result;
            } else {
                throw new FavoriteNotFoundException("Cannot delete, favorite id not found");
            }
        }
        catch (FavoriteNotFoundException e) {
            System.out.println(e);
            throw new FavoriteNotFoundException(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e);
            throw new Exception();
        }
    }
}