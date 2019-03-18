package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Book;

import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbcOperations;

    private static final RowMapper<Book> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Book.class);


    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("SELECT * FROM Book b LEFT JOIN Author a ON b.author_id=a.id LEFT JOIN Genre g ON b.genre_id=g.id",
                (rs, i) -> new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("author_id"),
                        rs.getInt("genre_id")
                )
        );
    }
}
