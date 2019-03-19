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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAll() {
        assertThat(genreDao.getAll().toString()).isEqualTo(Arrays.asList(GENRE1, GENRE2).toString());
    }

    @Test
    void getById() {
        assertThat(genreDao.getById(GENRE1.getId()).getId()).isEqualTo(GENRE1.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> genreDao.getById(-1));
    }

    @Test
    void insert() {
        int sizeBeforeInsert = genreDao.getAll().size();
        genreDao.insert(NEW_GENRE);
        assertThat(genreDao.getById(NEW_GENRE.getId()).getId()).isEqualTo(NEW_GENRE.getId());
        assertThat(genreDao.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = genreDao.getAll().size();
        genreDao.deleteById(GENRE1.getId());
        assertThat(genreDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
