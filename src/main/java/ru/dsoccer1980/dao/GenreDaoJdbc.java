package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        Map<String, Object> params = new ConcurrentHashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());

        jdbcOperations.update("INSERT INTO Genre(id, name) VALUES(:id,:name)", params);
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("SELECT * FROM Genre", (rs, i) -> new Genre(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM Genre WHERE id=:id", params);
    }
}
