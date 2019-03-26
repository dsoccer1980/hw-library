package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    void insert(String name);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    long getIdByName(String name);

    Genre getByName(String name);

}
