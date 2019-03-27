package ru.dsoccer1980.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Comment;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.TestData.*;

@DataJpaTest
@Import(CommentDaoJpa.class)
@ActiveProfiles("test")
public class CommentDaoImplTest {

    @Autowired
    private CommentDao commentDao;


    @Test
    void insertAndGetByBookId() {
        commentDao.insert(COMMENT1, BOOK1.getId());
        assertThat(commentDao.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }

    @Test
    void insertContent() {
        commentDao.insert(COMMENT1.getContent(), BOOK1.getId());
        assertThat(commentDao.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }

    @Test
    void insertNewComment() {
        commentDao.insert(new Comment("comment"), BOOK1.getId());
        assertThat(commentDao.getByBookId(BOOK1.getId()).size()).isEqualTo(1);
    }


}