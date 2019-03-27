package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(authorRepository.getById(AUTHOR2.getId()).getId()).isEqualTo(AUTHOR2.getId());
    }

    @Test
    void getByWrongId() {
        assertThat(authorRepository.getById(-1)).isEqualTo(null);
    }

    @Test
    void insert() {
        int sizeBeforeInsert = authorRepository.getAll().size();
        authorRepository.insert(NEW_AUTHOR);
        assertThat(authorRepository.getById(NEW_AUTHOR.getId()).getId()).isEqualTo(NEW_AUTHOR.getId());
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void insertName() {
        int sizeBeforeInsert = authorRepository.getAll().size();
        authorRepository.insert(NEW_AUTHOR.getName());
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = authorRepository.getAll().size();
        authorRepository.deleteById(AUTHOR2.getId());
        assertThat(authorRepository.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void contexLoads() throws Exception {
    }

}
