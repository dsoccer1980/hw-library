package ru.dsoccer1980.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbcOperations;
    private RowMapper<Genre> genreRowMapper = (rs, i) -> new Genre(rs.getInt("id"), rs.getString("name"));

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());

        jdbcOperations.update("INSERT INTO Genre(id, name) VALUES(:id,:name)", params);
    }

    @Override
    public void insert(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);

        jdbcOperations.update("INSERT INTO Genre(name) VALUES(:name)", params);

    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject("SELECT * FROM Genre WHERE id=:id", params, genreRowMapper);
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("SELECT * FROM Genre", genreRowMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM Genre WHERE id=:id", params);
    }

    @Override
    public long getIdByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return jdbcOperations.queryForObject("SELECT id FROM Genre WHERE name=:name", params, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }
}
