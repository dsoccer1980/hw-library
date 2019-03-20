package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    void insert(String name);

    Author getById(int id);

    List<Author> getAll();

    void deleteById(int id);

}
