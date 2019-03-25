package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.dao.GenreDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class GenreActionServiceImpl implements GenreActionService {

    private final GenreDao genreDao;

    public GenreActionServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public void action(String type, Integer id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(genreDao.getById(id));
                break;
            case "--getAll":
                genreDao.getAll().forEach(System.out::println);
                break;
            case "--delete":
                genreDao.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert genre name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                genreDao.insert(name);
                break;
            case "--count":
                System.out.println(genreDao.getAll().size());
        }
    }
}
