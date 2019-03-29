package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> getById(long id);

    void insert(Book book);

    List<Book> getAll();

    void deleteById(long id);

    List<Book> getByAuthorId(long id);

    List<Book> getByGenreId(long id);
}
