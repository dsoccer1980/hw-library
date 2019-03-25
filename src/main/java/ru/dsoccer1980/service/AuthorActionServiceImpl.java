package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.dao.AuthorDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AuthorActionServiceImpl implements AuthorActionService {

    private final AuthorDao authorDao;

    public AuthorActionServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void action(String type, Integer id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(authorDao.getById(id));
                break;
            case "--getAll":
                authorDao.getAll().forEach(System.out::println);
                break;
            case "--delete":
                authorDao.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert author name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                authorDao.insert(name);
                break;
            case "--count":
                System.out.println(authorDao.getAll().size());
        }
    }
}
