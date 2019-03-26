package ru.dsoccer1980.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public void insert(String name) {
        entityManager.persist(new Genre(name));
    }

    @Override
    public Genre getById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("DELETE FROM Genre ag WHERE g.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long getIdByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre ag WHERE g.name=:name", Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult().getId();
    }
}
