package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbcOperations;

    private AuthorDao authorDao;
    private GenreDao genreDao;

    private RowMapper<Book> bookRowMapper = (rs, i) ->
            new Book(
                    rs.getInt("id"),
                    rs.getString("name"),
                    new Author(rs.getInt("author_id"), rs.getString("Author.name")),
                    new Genre(rs.getInt("genre_id"), rs.getString("Genre.name")));
    private final String QUERY_SELECT = "SELECT * FROM Book b LEFT JOIN Author a ON b.author_id=a.id LEFT JOIN Genre g ON b.genre_id=g.id";

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbcOperations = jdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject(QUERY_SELECT + " WHERE b.id=:id", params, bookRowMapper);
    }

    @Override
    public void insert(Book book) {
        insert(book.getName(), book.getAuthor().getName(), book.getGenre().getName());
    }

    @Override
    public void insert(String bookName, String authorName, String genreName) {
        long authorId = authorDao.getIdByName(authorName);
        if (authorId == -1) {
            authorDao.insert(authorName);
            authorId = authorDao.getIdByName(authorName);
        }
        int genreId = genreDao.getIdByName(genreName);
        if (genreId == -1) {
            genreDao.insert(genreName);
            genreId = genreDao.getIdByName(genreName);
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", bookName);
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);

        jdbcOperations.update("INSERT INTO Book(name, author_id, genre_id) VALUES(:name, :author_id, :genre_id)", params);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query(QUERY_SELECT, bookRowMapper);
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM Book WHERE id=:id", params);
    }

    @Override
    public List<Book> getByAuthorId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.query(QUERY_SELECT + " WHERE a.id=:id", params, bookRowMapper);
    }

    @Override
    public List<Book> getByGenreId(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.query(QUERY_SELECT + " WHERE g.id=:id", params, bookRowMapper);
    }

}
