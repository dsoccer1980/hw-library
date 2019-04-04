package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;

@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAll() {
        assertThat(authorRepository.findAll().toString()).isEqualTo(Arrays.asList(AUTHOR1, AUTHOR2, AUTHOR3).toString());
    }

    @Test
    void findById() {
        Author author = authorRepository.findById(AUTHOR2.getId()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(author.getId()).isEqualTo(AUTHOR2.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(NotFoundException.class, () -> authorRepository.findById(-1L).orElseThrow(() -> new NotFoundException("Author not found")));
    }

    @Test
    void save() {
        int sizeBeforeInsert = authorRepository.findAll().size();
        authorRepository.save(NEW_AUTHOR);
        Author newAuthor = authorRepository.findById(NEW_AUTHOR.getId()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(newAuthor.getId()).isEqualTo(NEW_AUTHOR.getId());
        assertThat(authorRepository.findAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void saveExistAuthor() {
        int sizeBeforeInsert = authorRepository.findAll().size();
        authorRepository.save(AUTHOR1);
        Author getAuthor = authorRepository.findById(AUTHOR1.getId()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(getAuthor.getId()).isEqualTo(AUTHOR1.getId());
        assertThat(authorRepository.findAll().size()).isEqualTo(sizeBeforeInsert);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = authorRepository.findAll().size();
        authorRepository.deleteById(AUTHOR2.getId());
        assertThat(authorRepository.findAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void deleteByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> authorRepository.deleteById(-1L));
    }

    @Test
    void findByName() {
        Author author = authorRepository.findByName(AUTHOR1.getName()).orElseThrow(() -> new NotFoundException("Author not found"));
        assertThat(author.toString()).isEqualTo(AUTHOR1.toString());
    }

    @Test
    void getByWrongName() {
        assertThrows(NotFoundException.class, () -> authorRepository.findByName("Wrong name").orElseThrow(() -> new NotFoundException("Author not found")));
    }

}
