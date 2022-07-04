DROP TABLE IF EXISTS filmland_category;

CREATE TABLE filmland_category (
  category_id INT AUTO_INCREMENT  PRIMARY KEY,
  category_name VARCHAR(250) NOT NULL,
  available_content INT NOT NULL,
  price INT DEFAULT 0
);