package ru.dsoccer1980.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

import javax.persistence.*;

import java.util.List;

@Repository
@Transactional
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Author author) {
        if (author.isNew()) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Override
    public void insert(String name) {
        em.persist(new Author(name));
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

}
