package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(int id);

    void insert(Book book);

    List<Book> getAll();

    void deleteById(int id);

    List<Book> getByAuthorId(int id);

    List<Book> getByGenreId(int id);
}
