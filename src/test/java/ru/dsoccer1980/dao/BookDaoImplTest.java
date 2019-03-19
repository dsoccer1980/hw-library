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
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
public class BookDaoImplTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getAll() {
        Author author1 = new Author(1, "Стругацкий");
        Author author2 = new Author(2, "Уэллс");
        Author author3 = new Author(3, "Пушкин");
        Genre genre1 = new Genre(10, "Фантастика");
        Genre genre2 = new Genre(11, "Классика");
        Book book1 = new Book(100, "Трудно быть Богом", author1, genre1);
        Book book2 = new Book(101, "Машина времени", author2, genre1);
        Book book3 = new Book(102, "Онегин", author3, genre2);

        assertThat(bookDao.getAll().toString()).isEqualTo(Arrays.asList(book1, book2, book3).toString());
    }

    @Test
    void getById() {
        assertThat(bookDao.getById(100).getId()).isEqualTo(100);
    }

    @Test
    void insert() {
        Author author1 = new Author(1, "Стругацкий");
        Genre genre1 = new Genre(10, "Фантастика");
        int countBookBefore = bookDao.getAll().size();

        bookDao.insert(new Book(103, "Новая книга", author1, genre1));
        assertThat(bookDao.getAll().size()).isEqualTo(countBookBefore + 1);
    }

    @Test
    void deleteById() {
        int countBookBefore = bookDao.getAll().size();
        bookDao.deleteById(100);
        assertThat(bookDao.getAll().size()).isEqualTo(countBookBefore - 1);
    }
}
