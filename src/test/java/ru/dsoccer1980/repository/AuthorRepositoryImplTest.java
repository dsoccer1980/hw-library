package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@ActiveProfiles("test")
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getAll() {
        assertThat(authorRepository.getAll().toString()).isEqualTo(Arrays.asList(AUTHOR1, AUTHOR2, AUTHOR3).toString());
    }

    @Test
    void getById() {
        Author author = authorRepository.getById(AUTHOR2.getId()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(author.getId()).isEqualTo(AUTHOR2.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(RuntimeException.class, () -> authorRepository.getById(-1).orElseThrow(() -> new NotFoundException("Author not found")));
    }

    @Test
    void insert() {
        int sizeBeforeInsert = authorRepository.getAll().size();
        authorRepository.insert(NEW_AUTHOR);
        Author newAuthor = authorRepository.getById(NEW_AUTHOR.getId()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(newAuthor.getId()).isEqualTo(NEW_AUTHOR.getId());
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void insertName() {
        int sizeBeforeInsert = authorRepository.getAll().size();
        authorRepository.insert(NEW_AUTHOR);
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = authorRepository.getAll().size();
        authorRepository.deleteById(AUTHOR2.getId());
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }


}
