package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> params = new ConcurrentHashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());

        jdbcOperations.update("INSERT INTO Author(id, name) VALUES(:id,:name)", params);

    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query("SELECT * FROM Author", (rs, i) -> new Author(rs.getInt("id"), rs.getString("name")));
    }
}
