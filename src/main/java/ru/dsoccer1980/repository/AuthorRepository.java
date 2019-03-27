package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Author;

import java.util.List;

public interface AuthorRepository {

    void insert(Author author);

    void insert(String name);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    Author getByName(String name);

}
