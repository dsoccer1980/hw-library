package ru.dsoccer1980.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Comment;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

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
    void insertContent() {
        commentRepository.insert(COMMENT1.getContent(), BOOK1.getId());
        assertThat(commentRepository.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }

    @Test
    void insertNewComment() {
        commentRepository.insert(new Comment("comment"), BOOK1.getId());
        assertThat(commentRepository.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }


}
