package ru.dsoccer1980.dao;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dsoccer1980.domain.Author;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getById() {
        assertThat(authorDao.getById(2).getId()).isEqualTo(2);
    }

    @Test
    void insert() {
        authorDao.insert(new Author(3, "Новый автор"));
        assertThat(authorDao.getById(3).getId()).isEqualTo(3);
    }

    @Test
    void getAll() {
        Author author1 = new Author(1, "Стругацкий");
        Author author2 = new Author(2, "Стругацкий2");

        assertThat(authorDao.getAll().toString()).isEqualTo(Arrays.asList(author1, author2).toString());
    }

    @Test
    void deleteById() {
        authorDao.deleteById(2);
        Author author1 = new Author(1, "Стругацкий");
        assertThat(authorDao.getAll().toString()).isEqualTo(Arrays.asList(author1).toString());
    }

    @Test
    void contexLoads() throws Exception {
    }

}
