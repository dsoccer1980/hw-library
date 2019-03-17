package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    List<Genre> getAll();

    void deleteById(int id);

}
