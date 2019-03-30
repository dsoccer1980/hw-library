package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author insert(Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    Optional<Author> getByName(String name);

    Author getByNameOrElseCreate(String name);

}
