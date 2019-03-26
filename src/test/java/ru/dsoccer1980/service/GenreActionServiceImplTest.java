package ru.dsoccer1980.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dsoccer1980.util.ConfigurableInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
class GenreActionServiceImplTest {

    @Autowired
    private GenreActionService genreActionService;
    private ByteArrayOutputStream out;
    private ConfigurableInputStream in;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = new ConfigurableInputStream();
        System.setIn(in);
    }

    @Test
    void actionGet() throws IOException {
        genreActionService.action("--get", GENRE1.getId());
        assertThat(getData()).isEqualTo(GENRE1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        genreActionService.action("--getAll", -1L);
        assertThat(getData()).isEqualTo((GENRE1.toString() + GENRE2));
    }

    @Test
    void actionDelete() throws IOException {
        genreActionService.action("--delete", GENRE1.getId());
        genreActionService.action("--getAll", -1L);
        assertThat(getData()).isEqualTo((GENRE2.toString()));
    }

    @Test
    void actionCount() throws IOException {
        genreActionService.action("--count", -1L);
        assertThat(getData()).isEqualTo("2");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_GENRE.getName());
        genreActionService.action("--insert", -1L);

        out.reset();
        genreActionService.action("--count", -1L);
        assertThat(getData()).isEqualTo("3");
    }

    private String getData() {
        return new String(out.toByteArray(), StandardCharsets.UTF_8).replaceAll("[\\r\\n]+", "");
    }
}