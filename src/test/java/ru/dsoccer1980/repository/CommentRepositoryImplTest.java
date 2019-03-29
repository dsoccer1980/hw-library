package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.BOOK1;
import static ru.dsoccer1980.TestData.COMMENT1;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
@ActiveProfiles("test")
public class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository commentRepository;


    @Test
    void insertAndGetByBookId() {
        commentRepository.insert(COMMENT1, BOOK1.getId());
        assertThat(commentRepository.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }

    @Test
    void getByWrongBookId() {
        assertThat(commentRepository.getByBookId(-1L)).isEqualTo(Collections.emptyList());
    }


}
