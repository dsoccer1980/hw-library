package ru.dsoccer1980;

import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Comment;
import ru.dsoccer1980.domain.Genre;

public class TestData {

    public static final Author AUTHOR1 = new Author(1L, "Стругацкий");
    public static final Author AUTHOR2 = new Author(2L, "Уэллс");
    public static final Author AUTHOR3 = new Author(3L, "Пушкин");
    public static final Author NEW_AUTHOR = new Author(4L, "Новый автор");
    public static final Genre GENRE1 = new Genre(10L, "Фантастика");
    public static final Genre GENRE2 = new Genre(11L, "Классика");
    public static final Genre NEW_GENRE = new Genre(12L, "Новый жанр");
    public static final Book BOOK1 = new Book(100L, "Трудно быть Богом", AUTHOR1, GENRE1);
    public static final Book BOOK2 = new Book(101L, "Машина времени", AUTHOR2, GENRE1);
    public static final Book BOOK3 = new Book(102L, "Онегин", AUTHOR3, GENRE2);
    public static final Book NEW_BOOK = new Book(103L, "Новая книга", AUTHOR3, GENRE2);
    public static final Comment COMMENT1 = new Comment(1000L, "Комментарий один");
}
