package ru.dsoccer1980.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
@Transactional
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Author author) {
        entityManager.persist(author);
    }

    @Override
    public void insert(String name) {
        entityManager.persist(new Author(name));
    }

    @Override
    public Author getById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("DELETE FROM Author a WHERE a.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long getIdByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.name=:name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult().getId();
    }
}
