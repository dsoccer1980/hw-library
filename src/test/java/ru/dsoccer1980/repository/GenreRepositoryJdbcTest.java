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
@Import(GenreRepositoryJpa.class)
@ActiveProfiles("test")
public class GenreRepositoryJdbcTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void getAll() {
        assertThat(genreRepository.getAll().toString()).isEqualTo(Arrays.asList(GENRE1, GENRE2).toString());
    }

    @Test
    void getById() {
        assertThat(genreRepository.getById(GENRE1.getId()).getId()).isEqualTo(GENRE1.getId());
    }

    @Test
    void getByWrongId() {
        assertThat(genreRepository.getById(-1)).isEqualTo(null);
    }

    @Test
    void insert() {
        int sizeBeforeInsert = genreRepository.getAll().size();
        genreRepository.insert(NEW_GENRE);
        assertThat(genreRepository.getById(NEW_GENRE.getId()).getId()).isEqualTo(NEW_GENRE.getId());
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void insertName() {
        int sizeBeforeInsert = genreRepository.getAll().size();
        genreRepository.insert(NEW_GENRE);
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = genreRepository.getAll().size();
        genreRepository.deleteById(GENRE1.getId());
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }


}
