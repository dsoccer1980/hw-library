package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre insert(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    Optional<Genre> getByName(String name);

    Genre getByNameOrElseCreate(String name);
}
