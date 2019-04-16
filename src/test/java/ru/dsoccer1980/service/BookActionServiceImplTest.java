package ru.dsoccer1980.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.repository.BookRepository;
import ru.dsoccer1980.util.ConfigurableInputStream;
import ru.dsoccer1980.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

class BookActionServiceImplTest extends AbstractServiceTest {

    @Autowired
    BookRepository bookRepository;
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

        bookRepository.deleteAll();
        bookRepository.save(BOOK1);
        bookRepository.save(BOOK2);
        bookRepository.save(BOOK3);
    }

    @Test
    void actionGet() throws IOException {
        bookActionService.action("--get", BOOK1.getId());
        assertThat(Util.getData(out)).isEqualTo(BOOK1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        bookActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).isEqualTo((BOOK1.toString() + BOOK2 + BOOK3));
    }

    @Test
    void actionGetByAuthor() throws IOException {
        bookActionService.action("--author", BOOK1.getAuthor().getId());
        assertThat(Util.getData(out)).endsWith(BOOK1.toString());
    }

    @Test
    void actionGetByGenre() throws IOException {
        bookActionService.action("--genre", BOOK1.getGenre().getId());
        assertThat(Util.getData(out)).isEqualTo(BOOK1.toString() + BOOK2);
    }

    @Test
    void actionDelete() throws IOException {
        bookActionService.action("--delete", BOOK1.getId());
        bookActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).isEqualTo((BOOK2.toString() + BOOK3));
    }

    @Test
    void actionCount() throws IOException {
        bookActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("3");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_BOOK.getName());
        in.add(NEW_BOOK.getAuthor().getName());
        in.add(NEW_BOOK.getGenre().getName());
        bookActionService.action("--insert", "-1");

        out.reset();
        bookActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("4");
    }

}