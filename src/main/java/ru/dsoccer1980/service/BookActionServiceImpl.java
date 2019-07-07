package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Genre;
import ru.dsoccer1980.repository.AuthorRepository;
import ru.dsoccer1980.repository.BookRepository;
import ru.dsoccer1980.repository.GenreRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class BookActionServiceImpl implements BookActionService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookActionServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public void action(String type, String id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found")));
                break;
            case "--getAll":
                bookRepository.findAll().forEach(System.out::println);
                break;
            case "--author":
                bookRepository.findByAuthorId(id).forEach(System.out::println);
                break;
            case "--genre":
                bookRepository.findByGenreId(id).forEach(System.out::println);
                break;
            case "--delete":
                bookRepository.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert book name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String bookName = reader.readLine();
                System.out.println("Please insert book author:");
                String authorName = reader.readLine();
                Optional<Author> authorByName = authorRepository.findByName(authorName);
                Author author = authorByName.orElseGet(() -> authorRepository.save(new Author(authorName)));
                System.out.println("Please insert book genre:");
                String genreName = reader.readLine();
                Optional<Genre> genreByName = genreRepository.findByName(genreName);
                Genre genre = genreByName.orElseGet(() -> genreRepository.save(new Genre(genreName)));
                bookRepository.save(
                        new Book(
                                bookName,
                                author,
                                genre));
                break;
            case "--count":
                System.out.println(bookRepository.findAll().size());
                break;
            default:
                throw new NotFoundException("Operation not found");
        }
    }
}
