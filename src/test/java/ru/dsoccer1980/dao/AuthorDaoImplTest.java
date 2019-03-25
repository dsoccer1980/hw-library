package ru.dsoccer1980.dao;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;

@JdbcTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(AuthorDaoJdbc.class)
@ActiveProfiles("test")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getAll() {
        assertThat(authorDao.getAll().toString()).isEqualTo(Arrays.asList(AUTHOR1, AUTHOR2, AUTHOR3).toString());
    }

    @Test
    void getById() {
        assertThat(authorDao.getById(AUTHOR2.getId()).getId()).isEqualTo(AUTHOR2.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(-1));
    }

    @Test
    void insert() {
        int sizeBeforeInsert = authorDao.getAll().size();
        authorDao.insert(NEW_AUTHOR);
        assertThat(authorDao.getById(NEW_AUTHOR.getId()).getId()).isEqualTo(NEW_AUTHOR.getId());
        assertThat(authorDao.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void insertName() {
        int sizeBeforeInsert = authorDao.getAll().size();
        authorDao.insert(NEW_AUTHOR.getName());
        assertThat(authorDao.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = authorDao.getAll().size();
        authorDao.deleteById(AUTHOR2.getId());
        assertThat(authorDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void getIdByName() {
        assertThat(authorDao.getIdByName(AUTHOR1.getName())).isEqualTo(AUTHOR1.getId());
    }

    @Test
    void getIdByWrongName() {
        assertThat(authorDao.getIdByName("wrong name")).isEqualTo(-1);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
