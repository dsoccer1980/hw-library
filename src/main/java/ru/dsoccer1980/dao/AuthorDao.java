package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(int id);

    List<Author> getAll();

    void deleteById(int id);

}
