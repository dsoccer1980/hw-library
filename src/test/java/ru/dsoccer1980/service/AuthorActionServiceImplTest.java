package ru.dsoccer1980.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.repository.AuthorRepository;
import ru.dsoccer1980.util.ConfigurableInputStream;
import ru.dsoccer1980.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;


class AuthorActionServiceImplTest extends AbstractServiceTest {

    @Autowired
    private AuthorActionService authorActionService;
    private ByteArrayOutputStream out;
    private ConfigurableInputStream in;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = new ConfigurableInputStream();
        System.setIn(in);

        authorRepository.deleteAll();
        authorRepository.save(AUTHOR1);
        authorRepository.save(AUTHOR2);
        authorRepository.save(AUTHOR3);
    }

    @Test
    void actionGet() throws IOException {
        authorActionService.action("--get", AUTHOR1.getId());
        assertThat(Util.getData(out)).endsWith(AUTHOR1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        authorActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).isEqualTo((AUTHOR1.toString() + AUTHOR2 + AUTHOR3));
    }

    @Test
    void actionDelete() throws IOException {
        authorActionService.action("--delete", AUTHOR1.getId());
        out.reset();
        authorActionService.action("--getAll", "-1");
        assertThat(Util.getData(out)).contains((AUTHOR2.toString() + AUTHOR3));
    }

    @Test
    void actionCount() throws IOException {
        authorActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("3");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_AUTHOR.getName());
        authorActionService.action("--insert", "-1");

        out.reset();
        authorActionService.action("--count", "-1");
        assertThat(Util.getData(out)).isEqualTo("4");
    }
}