package ru.dsoccer1980.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author insert(Author author) {
        if (author.hasNullId()) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("DELETE FROM Author a WHERE a.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Author getByName(String name) {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.name=:name", Author.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Author getByNameOrElseCreate(String name) {
        Author author = getByName(name);
        if (author == null) {
            return insert(new Author(name));
        }
        return author;
    }

}
