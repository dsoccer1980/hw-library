DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Genre;

CREATE TABLE Author(
  id INT PRIMARY KEY ,
  name VARCHAR(255)
);

CREATE TABLE Genre(
  id INT PRIMARY KEY ,
  name VARCHAR(255)
)