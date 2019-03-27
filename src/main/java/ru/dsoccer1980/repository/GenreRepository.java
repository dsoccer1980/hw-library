package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Genre;

import java.util.List;

public interface GenreRepository {

    void insert(Genre genre);

    void insert(String name);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    Genre getByName(String name);

}
