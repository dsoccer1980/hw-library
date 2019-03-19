package ru.dsoccer1980;

import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;

public class TestData {

    public static final Author AUTHOR1 = new Author(1, "Стругацкий");
    public static final Author AUTHOR2 = new Author(2, "Уэллс");
    public static final Author AUTHOR3 = new Author(3, "Пушкин");
    public static final Author NEW_AUTHOR = new Author(4, "Новый автор");
    public static final Genre GENRE1 = new Genre(10, "Фантастика");
    public static final Genre GENRE2 = new Genre(11, "Классика");
    public static final Book BOOK1 = new Book(100, "Трудно быть Богом", AUTHOR1, GENRE1);
    public static final Book BOOK2 = new Book(101, "Машина времени", AUTHOR2, GENRE1);
    public static final Book BOOK3 = new Book(102, "Онегин", AUTHOR3, GENRE2);
}
