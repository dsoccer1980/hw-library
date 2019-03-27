package ru.dsoccer1980.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Comment comment, Long book_id) {
        comment.setBook(em.getReference(Book.class, book_id));
        if (comment.isNew()) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public void insert(String content, Long book_id) {
        insert(new Comment(content), book_id);
    }
}
