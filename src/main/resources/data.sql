DELETE FROM Book;
DELETE FROM Author;
DELETE FROM Genre;

INSERT INTO Author(id, name) VALUES (1, 'Стругацкий');
INSERT INTO Author(id, name) VALUES (2, 'Уэллс');
INSERT INTO Author(id, name) VALUES (3, 'Пушкин');

INSERT INTO Genre(id, name) VALUES (10, 'Фантастика');
INSERT INTO Genre(id, name) VALUES (11, 'Классика');

INSERT INTO Book(id, name, author_id, genre_id)   VALUES (100, 'Трудно быть Богом', 1, 10);
INSERT INTO Book(id, name, author_id, genre_id)   VALUES (101, 'Машина времени', 2, 10);
INSERT INTO Book(id, name, author_id, genre_id)   VALUES (102, 'Онегин', 3, 11);



