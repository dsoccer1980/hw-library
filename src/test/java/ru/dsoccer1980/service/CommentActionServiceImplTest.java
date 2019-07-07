package ru.dsoccer1980.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.BOOK1;
import static ru.dsoccer1980.TestData.COMMENT1;

class CommentActionServiceImplTest extends AbstractServiceTest {

    @Autowired
    private CommentActionService commentActionService;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void actionInsert() throws IOException {
        commentActionService.action("--insert", COMMENT1.getContent(), BOOK1.getId());
        commentActionService.action("--get", "", BOOK1.getId());
        assertThat(Util.getData(out).contains(COMMENT1.getContent()));
    }


}