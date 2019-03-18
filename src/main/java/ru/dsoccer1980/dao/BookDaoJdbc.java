package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;

import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        jdbcOperations.update("INSERT INTO Book(id, name, author_id, genre_id) VALUES(:id, :name, :author_id, :genre_id)", params);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("SELECT * FROM Book b LEFT JOIN Author a ON b.author_id=a.id LEFT JOIN Genre g ON b.author_id=g.id",
                (rs, i) ->
                        new Book(
                                rs.getInt("id"),
                                rs.getString("name"),
                                new Author(rs.getInt("author_id"), rs.getString("Author.name")),
                                new Genre(rs.getInt("genre_id"), rs.getString("Genre.name")))
        );
    }

}
