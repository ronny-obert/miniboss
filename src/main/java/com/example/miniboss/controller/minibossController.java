package com.example.miniboss.controller;

import com.example.miniboss.exception.FavoriteNotFoundException;
import com.example.miniboss.model.Fact;
import com.example.miniboss.model.Favorite;
import com.example.miniboss.repository.FactRepository;
import com.example.miniboss.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/miniboss")
public class minibossController {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FactRepository factRepository;

    @GetMapping("/facts/random")
    public Fact getFactRandom() {
        Optional<Fact> fact = factRepository.findRandom();
        return fact.orElseThrow();
    }

    @GetMapping("/facts/{id}")
    public Fact getFactById(@PathVariable String id) {
        Optional<Fact> fact = factRepository.findAllById(id);
        return fact.orElseThrow();
    }

    @GetMapping("/favorites")
    public List<Favorite> getFavoriteAll() {
        Optional<List> favorite = favoriteRepository.findAll();
        return favorite.orElseThrow();
    }

    @GetMapping("/favorites/{id}")
    public Favorite getFavoriteById(@PathVariable Integer id) {
        Optional<Favorite> favorite = favoriteRepository.findAllById(id);
        return favorite.orElseThrow();
    }

    @DeleteMapping("/favorites/{id}")
    public Integer deleteFavoriteById(@PathVariable Integer id) throws Exception {
        if(!favoriteRepository.findAllById(id).isPresent()){
            throw new FavoriteNotFoundException("Favorite not found");
        }
        return favoriteRepository.deleteById(id);
    }

    @PostMapping("/favorites")
    @ResponseStatus(HttpStatus.CREATED)
    public Favorite create(@RequestBody Favorite favorite) throws Exception {
        return favoriteRepository.save(favorite);
    }
}
