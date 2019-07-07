package ru.dsoccer1980.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.domain.Comment;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;


public class CommentRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void populateData() {
        bookRepository.deleteAll();
        bookRepository.save(BOOK1);
        bookRepository.save(BOOK2);
        bookRepository.save(BOOK3);
        commentRepository.deleteAll();
    }

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

    @Test
    @DisplayName("При удалении книги должен удалиться комментарий к нему")
    void deleteById() {
        int sizeBeforeDelete = bookRepository.findAll().size();
        commentRepository.save(new Comment("New Comment", BOOK1));
        assertThat(commentRepository.findByBookId(BOOK1.getId())).hasSize(1);

        bookRepository.deleteById(BOOK1.getId());
        assertThat(bookRepository.findAll().size()).isEqualTo(sizeBeforeDelete - 1);
        assertThat(commentRepository.findByBookId(BOOK1.getId())).hasSize(0);
    }


}
