
DROP TABLE IF EXISTS tbl_user_profile;

CREATE TABLE tbl_user_profile (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) DEFAULT NULL,
  last_name VARCHAR(250) DEFAULT NULL

);

INSERT INTO tbl_user_profile (id, first_name, last_name) VALUES   ('1','Ronny','Obert');

---------------------------------------

DROP TABLE IF EXISTS tbl_favorite_fact;

CREATE TABLE tbl_favorite_fact (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT DEFAULT NULL,
  fact_id VARCHAR(250) DEFAULT NULL,
  text VARCHAR(250) DEFAULT NULL,
  created_by VARCHAR(250) DEFAULT 'SYSTEM',
  created_datetime DATETIME DEFAULT NULL,
  updated_by VARCHAR(250) DEFAULT NULL,
  updated_datetime DATETIME DEFAULT NULL,
  UNIQUE (user_id, fact_id),
  FOREIGN KEY (user_id)
          REFERENCES tbl_user_profile(id)
          ON DELETE CASCADE
);