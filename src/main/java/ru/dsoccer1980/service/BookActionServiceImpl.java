package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.repository.AuthorRepository;
import ru.dsoccer1980.repository.BookRepository;
import ru.dsoccer1980.repository.GenreRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void action(String type, Long id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(bookRepository.getById(id));
                break;
            case "--getAll":
                bookRepository.getAll().forEach(System.out::println);
                break;
            case "--author":
                bookRepository.getByAuthorId(id).forEach(System.out::println);
                break;
            case "--genre":
                bookRepository.getByGenreId(id).forEach(System.out::println);
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
                System.out.println("Please insert book genre:");
                String geneName = reader.readLine();
                bookRepository.insert(
                        new Book(
                                bookName,
                                authorRepository.getByNameOrElseCreate(authorName),
                                genreRepository.getByNameOrElseCreate(geneName)));
                break;
            case "--count":
                System.out.println(bookRepository.getAll().size());
        }
    }
}
