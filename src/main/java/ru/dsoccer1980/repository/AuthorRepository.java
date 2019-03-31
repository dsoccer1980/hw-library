package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    Author save(Author save);

    @Override
    Optional<Author> findById(Long id);

    @Override
    List<Author> findAll();

    void deleteById(Long id);

    Optional<Author> findByName(String name);

}
