package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(long id);

    void insert(Book book);

    void insert(String bookName, String authorName, String genreName);

    List<Book> getAll();

    void deleteById(long id);

    List<Book> getByAuthorId(long id);

    List<Book> getByGenreId(long id);
}
