package ru.dsoccer1980.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    private AuthorDao authorDao;
    private GenreDao genreDao;

    public BookDaoJpa(AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    private final String QUERY_SELECT = "SELECT b FROM Book b LEFT JOIN Author a ON b.author.id =a.id LEFT JOIN Genre g ON b.genre.id=g.id";

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery(QUERY_SELECT + " WHERE b.id=:id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void insert(Book book) {
        if (book.isNew()) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public void insert(String bookName, String authorName, String genreName) {
        Genre genre = genreDao.getByName(genreName);
        if (genre == null) {
            genreDao.insert(genreName);
            genre = genreDao.getByName(genreName);
        }
        Author author = authorDao.getByName(authorName);
        if (author == null) {
            authorDao.insert(authorName);
            author = authorDao.getByName(authorName);
        }

        insert(new Book(bookName, author, genre));
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
