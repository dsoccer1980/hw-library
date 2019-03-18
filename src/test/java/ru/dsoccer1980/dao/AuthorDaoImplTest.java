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
import ru.dsoccer1980.domain.Author;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getById() {
        assertThat(authorDao.getById(2).getId()).isEqualTo(2);
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(4));
    }

    @Test
    void insert() {
        authorDao.insert(new Author(4, "Новый автор"));
        assertThat(authorDao.getById(4).getId()).isEqualTo(4);
        assertThat(authorDao.getAll().size()).isEqualTo(4);
    }

    @Test
    void getAll() {
        Author author1 = new Author(1, "Стругацкий");
        Author author2 = new Author(2, "Уэллс");
        Author author3 = new Author(3, "Пушкин");

        assertThat(authorDao.getAll().toString()).isEqualTo(Arrays.asList(author1, author2, author3).toString());
    }

    @Test
    void deleteById() {
        authorDao.deleteById(2);
        assertThat(authorDao.getAll().size()).isEqualTo(2);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
