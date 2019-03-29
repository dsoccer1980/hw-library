package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    Genre getByName(String name);

    Genre getByNameOrElseCreate(String name);
}
