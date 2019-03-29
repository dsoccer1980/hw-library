package ru.dsoccer1980.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;

@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
@ActiveProfiles("test")
public class BookRepositoryImplTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void getAll() {
        assertThat(bookRepository.getAll().toString()).isEqualTo(Arrays.asList(BOOK1, BOOK2, BOOK3).toString());
    }

    @Test
    void getById() {
        Book book = bookRepository.getById(BOOK1.getId()).orElseThrow(() -> new NotFoundException("Book not found"));
        assertThat(book.getId()).isEqualTo(BOOK1.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(NotFoundException.class, () -> bookRepository.getById(-1).orElseThrow(() -> new NotFoundException("Book not found")));
    }

    @Test
    void insert() {
        int sizeBeforeInsert = bookRepository.getAll().size();
        bookRepository.insert(NEW_BOOK);
        assertThat(bookRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = bookRepository.getAll().size();
        bookRepository.deleteById(BOOK1.getId());
        assertThat(bookRepository.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void getByAuthorId() {
        assertThat(bookRepository.getByAuthorId(AUTHOR1.getId()).toString()).isEqualTo(Arrays.asList(BOOK1).toString());
    }

    @Test
    void getByGenreId() {
        assertThat(bookRepository.getByGenreId(GENRE1.getId()).toString()).isEqualTo(Arrays.asList(BOOK1, BOOK2).toString());
    }
}
