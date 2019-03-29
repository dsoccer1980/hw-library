package ru.dsoccer1980.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Genre;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre insert(Genre genre) {
        if (genre.hasNullId()) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
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

    @Override
    public Genre getByNameOrElseCreate(String name) {
        Genre genre = getByName(name);
        if (genre == null) {
            return insert(new Genre(name));
        }
        return genre;
    }
}
