package ru.dsoccer1980.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Genre;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Genre genre) {
        if (genre.isNew()) {
            em.persist(genre);
        } else {
            em.merge(genre);
        }
    }

    @Override
    public void insert(String name) {
        em.persist(new Genre(name));
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("DELETE FROM Genre g WHERE g.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Genre getByName(String name) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.name=:name", Genre.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
