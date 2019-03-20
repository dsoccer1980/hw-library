package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    void insert(String name);

    Genre getById(int id);

    List<Genre> getAll();

    void deleteById(int id);

    int getIdByName(String name);

}
