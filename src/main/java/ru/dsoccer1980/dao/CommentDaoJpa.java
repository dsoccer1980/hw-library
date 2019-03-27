package ru.dsoccer1980.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Comment comment, Long bookId) {
        comment.setBook(em.getReference(Book.class, bookId));
        if (comment.isNew()) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public void insert(String content, Long bookId) {
        insert(new Comment(content), bookId);
    }

    @Override
    public List<Comment> getByBookId(Long bookId) {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c JOIN FETCH c.book WHERE c.book.id=:bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
