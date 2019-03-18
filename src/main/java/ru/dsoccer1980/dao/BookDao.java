package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    List<Book> getAll();
}
