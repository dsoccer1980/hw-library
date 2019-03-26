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

import static org.assertj.core.api.Assertions.assertThat;


import java.io.*;
import java.nio.charset.StandardCharsets;

import static ru.dsoccer1980.TestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
class BookActionServiceImplTest {

    @Autowired
    private BookActionService bookActionService;
    private ByteArrayOutputStream out;
    private ConfigurableInputStream in;


    @BeforeEach
    void setup() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = new ConfigurableInputStream();
        System.setIn(in);
    }

    @Test
    void actionGet() throws IOException {
        bookActionService.action("--get", BOOK1.getId());
        assertThat(getData()).isEqualTo(BOOK1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        bookActionService.action("--getAll", -1L);
        assertThat(getData()).isEqualTo((BOOK1.toString() + BOOK2 + BOOK3));
    }

    @Test
    void actionGetByAuthor() throws IOException {
        bookActionService.action("--author", BOOK1.getAuthor().getId());
        assertThat(getData()).isEqualTo(BOOK1.toString());
    }

    @Test
    void actionGetByGenre() throws IOException {
        bookActionService.action("--genre", BOOK1.getGenre().getId());
        assertThat(getData()).isEqualTo(BOOK1.toString() + BOOK2);
    }

    @Test
    void actionDelete() throws IOException {
        bookActionService.action("--delete", BOOK1.getId());
        bookActionService.action("--getAll", -1L);
        assertThat(getData()).isEqualTo((BOOK2.toString() + BOOK3));
    }

    @Test
    void actionCount() throws IOException {
        bookActionService.action("--count", -1L);
        assertThat(getData()).isEqualTo("3");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_BOOK.getName());
        in.add(NEW_BOOK.getAuthor().getName());
        in.add(NEW_BOOK.getGenre().getName());
        bookActionService.action("--insert", -1L);

        out.reset();
        bookActionService.action("--count", -1L);
        assertThat(getData()).isEqualTo("4");
    }


    private String getData() {
        return new String(out.toByteArray(), StandardCharsets.UTF_8).replaceAll("[\\r\\n]+", "");
    }

}