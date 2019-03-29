package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Author;

import java.util.List;

public interface AuthorRepository {

    Author insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    Author getByName(String name);

    Author getByNameOrElseCreate(String name);

}
