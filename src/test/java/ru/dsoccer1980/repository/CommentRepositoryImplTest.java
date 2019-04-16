package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.BOOK1;
import static ru.dsoccer1980.TestData.COMMENT1;


public class CommentRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void insertAndGetByBookId() {
        COMMENT1.setBook(BOOK1);
        commentRepository.save(COMMENT1);
        assertThat(commentRepository.findByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }

    @Test
    void getByWrongBookId() {
        assertThat(commentRepository.findByBookId("-1")).isEqualTo(Collections.emptyList());
    }


}
