package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.repository.AuthorRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AuthorActionServiceImpl implements AuthorActionService {

    private final AuthorRepository authorRepository;

    public AuthorActionServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void action(String type, Long id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found")));
                break;
            case "--getAll":
                authorRepository.findAll().forEach(System.out::println);
                break;
            case "--delete":
                authorRepository.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert author name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                authorRepository.save(new Author(name));
                break;
            case "--count":
                System.out.println(authorRepository.findAll().size());
        }
    }
}
