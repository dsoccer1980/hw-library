package ru.dsoccer1980.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

@DataJpaTest
@Import({BookDaoJpa.class, AuthorDaoJpa.class, GenreDaoJpa.class})
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

    @Test
    void getByAuthorId() {
        assertThat(bookDao.getByAuthorId(AUTHOR1.getId()).toString()).isEqualTo(Arrays.asList(BOOK1).toString());
    }

    @Test
    void getByGenreId() {
        assertThat(bookDao.getByGenreId(GENRE1.getId()).toString()).isEqualTo(Arrays.asList(BOOK1, BOOK2).toString());
    }
}
