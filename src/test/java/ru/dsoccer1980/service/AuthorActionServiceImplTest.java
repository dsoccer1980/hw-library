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
import ru.dsoccer1980.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = "classpath:data-test.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("test")
class AuthorActionServiceImplTest {

    @Autowired
    private AuthorActionService authorActionService;
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
        authorActionService.action("--get", AUTHOR1.getId());
        assertThat(Util.getData(out)).isEqualTo(AUTHOR1.toString());
    }

    @Test
    void actionGetAll() throws IOException {
        authorActionService.action("--getAll", -1L);
        assertThat(Util.getData(out)).isEqualTo((AUTHOR1.toString() + AUTHOR2 + AUTHOR3));
    }

    @Test
    void actionDelete() throws IOException {
        authorActionService.action("--delete", AUTHOR1.getId());
        out.reset();
        authorActionService.action("--getAll", -1L);
        assertThat(Util.getData(out)).isEqualTo((AUTHOR2.toString() + AUTHOR3));
    }

    @Test
    void actionCount() throws IOException {
        authorActionService.action("--count", -1L);
        assertThat(Util.getData(out)).isEqualTo("3");
    }

    @Test
    void actionInsert() throws IOException {
        in.add(NEW_AUTHOR.getName());
        authorActionService.action("--insert", -1L);

        out.reset();
        authorActionService.action("--count", -1L);
        assertThat(Util.getData(out)).isEqualTo("4");
    }
}