package ru.dsoccer1980.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Genre;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dsoccer1980.TestData.*;


@DataJpaTest
@Import(GenreRepositoryJpa.class)
@ActiveProfiles("test")
public class GenreRepositoryImplTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void getAll() {
        assertThat(genreRepository.getAll().toString()).isEqualTo(Arrays.asList(GENRE1, GENRE2).toString());
    }

    @Test
    void getById() {
        Genre genre = genreRepository.getById(GENRE1.getId()).orElseThrow(() -> new NotFoundException("Genre not found"));
        assertThat(genre.getId()).isEqualTo(GENRE1.getId());
    }

    @Test
    void getByWrongId() {
        assertThrows(NotFoundException.class, () -> genreRepository.getById(-1).orElseThrow(() -> new NotFoundException("Genre not found")));
    }

    @Test
    void insert() {
        int sizeBeforeInsert = genreRepository.getAll().size();
        Genre insertedGenre = genreRepository.insert(NEW_GENRE);
        assertThat(insertedGenre.getName()).isEqualTo(NEW_GENRE.getName());
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void insertExistGenre() {
        int sizeBeforeInsert = genreRepository.getAll().size();
        Genre insertedGenre = genreRepository.insert(GENRE1);
        assertThat(insertedGenre.getName()).isEqualTo(GENRE1.getName());
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeInsert);
    }

    @Test
    void deleteById() {
        int sizeBeforeDelete = genreRepository.getAll().size();
        genreRepository.deleteById(GENRE1.getId());
        assertThat(genreRepository.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void getByName() {
        Genre author = genreRepository.getByName(GENRE1.getName()).orElseThrow(() -> new NotFoundException("Genre not found"));
        assertThat(author.toString()).isEqualTo(GENRE1.toString());
    }

    @Test
    void getByWrongName() {
        assertThrows(NotFoundException.class, () -> genreRepository.getByName("Wrong name").orElseThrow(() -> new NotFoundException("Genre not found")));
    }

    @Test
    void getByNameOrElseCreateWithNewGenre() {
        Genre newGenre = genreRepository.getByNameOrElseCreate("New Genre");
        assertThat(newGenre.getName()).isEqualTo("New Genre");
    }

    @Test
    void getByNameOrElseCreateWithExistGenre() {
        Genre existGenre = genreRepository.getByNameOrElseCreate(GENRE1.getName());
        assertThat(existGenre.getName()).isEqualTo(GENRE1.getName());
    }


}
