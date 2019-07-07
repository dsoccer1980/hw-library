package ru.dsoccer1980.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.repository.GenreRepository;
import ru.dsoccer1980.util.ConfigurableInputStream;
import ru.dsoccer1980.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

class GenreActionServiceImplTest extends AbstractServiceTest {

    @Autowired
    private GenreActionService genreActionService;
    private ByteArrayOutputStream out;
    private ConfigurableInputStream in;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = new ConfigurableInputStream();
        System.setIn(in);

        genreRepository.deleteAll();
        genreRepository.save(GENRE1);
        genreRepository.save(GENRE2);
    }

    @Test
    void actionGet() throws IOException {
        genreActionService.action("--get", GENRE1.getId());
        assertThat(Util.getData(out)).isEqualTo(GENRE1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        genreActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).isEqualTo((GENRE1.toString() + GENRE2));
    }

    @Test
    void actionDelete() throws IOException {
        genreActionService.action("--delete", GENRE1.getId());
        out.reset();
        genreActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).endsWith((GENRE2.toString()));
    }

    @Test
    void actionCount() throws IOException {
        genreActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("2");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_GENRE.getName());
        genreActionService.action("--insert", "-1");

        out.reset();
        genreActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("3");
    }
}