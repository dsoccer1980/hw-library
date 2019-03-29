package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.repository.BookRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class BookActionServiceImpl implements BookActionService {

    private final BookRepository bookRepository;

    public BookActionServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
                bookRepository.insert(bookName, authorName, geneName);
                break;
            case "--count":
                System.out.println(bookRepository.getAll().size());
        }
    }
}
