package ru.dsoccer1980.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
public class BookDaoImplTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getAll() {
        assertThat(bookDao.getAll().toString()).isEqualTo(Arrays.asList(BOOK1, BOOK2, BOOK3).toString());
    }

    @Test
    void getById() {
        assertThat(bookDao.getById(BOOK1.getId()).getId()).isEqualTo(BOOK1.getId());
    }

    @Test
    void insert() {
        int sizeBeforeInsert = bookDao.getAll().size();
        bookDao.insert(NEW_BOOK);
        assertThat(bookDao.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = bookDao.getAll().size();
        bookDao.deleteById(BOOK1.getId());
        assertThat(bookDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }
}
