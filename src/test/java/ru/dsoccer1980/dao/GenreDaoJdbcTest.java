package ru.dsoccer1980.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dsoccer1980.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAll() {
        Genre genre1 = new Genre(10, "Фантастика");
        Genre genre2 = new Genre(11, "Классика");

        assertThat(genreDao.getAll().toString()).isEqualTo(Arrays.asList(genre1, genre2).toString());
    }

    @Test
    void getById() {
        assertThat(genreDao.getById(10).getId()).isEqualTo(10);
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> genreDao.getById(12));
    }

    @Test
    void insert() {
        genreDao.insert(new Genre(3, "Новый жанр"));
        assertThat(genreDao.getById(3).getId()).isEqualTo(3);
        assertThat(genreDao.getAll().size()).isEqualTo(3);
    }

    @Test
    void deleteById() {
        genreDao.deleteById(10);
        assertThat(genreDao.getAll().size()).isEqualTo(1);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
