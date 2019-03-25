package ru.dsoccer1980.dao;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;


@JdbcTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(GenreDaoJdbc.class)
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
    void insertName() {
        int sizeBeforeInsert = genreDao.getAll().size();
        genreDao.insert(NEW_AUTHOR.getName());
        assertThat(genreDao.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = genreDao.getAll().size();
        genreDao.deleteById(GENRE1.getId());
        assertThat(genreDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void getIdByName() {
        assertThat(genreDao.getIdByName(GENRE1.getName())).isEqualTo(GENRE1.getId());
    }

    @Test
    void getIdByWrongName() {
        assertThat(genreDao.getIdByName("wrong name")).isEqualTo(-1);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
