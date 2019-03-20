package ru.dsoccer1980.dao;

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
    public Genre getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject("SELECT * FROM Genre WHERE id=:id", params,
                (rs, i) -> new Genre(rs.getInt("id"), rs.getString("name")));
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
