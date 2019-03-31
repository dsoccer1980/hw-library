package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);

    @Override
    Book save(Book book);

    @Override
    List<Book> findAll();

    @Override
    void deleteById(Long id);

    List<Book> findByAuthorId(Long id);

    List<Book> findByGenreId(Long id);
}
