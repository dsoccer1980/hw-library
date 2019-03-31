package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    Genre save(Genre save);

    @Override
    Optional<Genre> findById(Long id);

    @Override
    List<Genre> findAll();

    @Override
    void deleteById(Long id);

    Optional<Genre> findByName(String name);

}
