package ru.dsoccer1980.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    private final String QUERY_SELECT = "SELECT b FROM Book b LEFT JOIN Author a ON b.author.id =a.id LEFT JOIN Genre g ON b.genre.id=g.id";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Book> getById(long id) {
        TypedQuery<Book> query = em.createQuery(QUERY_SELECT + " WHERE b.id=:id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Book book) {
        if (book.hasNullId()) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(QUERY_SELECT, Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("DELETE FROM Book b WHERE b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> getByAuthorId(long id) {
        TypedQuery<Book> query = em.createQuery(QUERY_SELECT + " WHERE a.id=:id", Book.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenreId(long id) {
        TypedQuery<Book> query = em.createQuery(QUERY_SELECT + " WHERE g.id=:id", Book.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
