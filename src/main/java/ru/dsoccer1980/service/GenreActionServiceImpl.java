package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Genre;
import ru.dsoccer1980.repository.GenreRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class GenreActionServiceImpl implements GenreActionService {

    private final GenreRepository genreRepository;

    public GenreActionServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void action(String type, String id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre not found")));
                break;
            case "--getAll":
                genreRepository.findAll().forEach(System.out::println);
                break;
            case "--delete":
                genreRepository.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert genre name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                genreRepository.save(new Genre(name));
                break;
            case "--count":
                System.out.println(genreRepository.findAll().size());
                break;
            default:
                throw new NotFoundException("Operation not found");
        }
    }
}
