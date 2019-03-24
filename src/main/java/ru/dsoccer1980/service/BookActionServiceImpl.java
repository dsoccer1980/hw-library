package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.dao.BookDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class BookActionServiceImpl implements BookActionService {

    private final BookDao bookDao;

    public BookActionServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void action(String type, Integer id) throws IOException {
        switch (type) {
            case "--get":
                System.out.println(bookDao.getById(id));
                break;
            case "--getAll":
                bookDao.getAll().forEach(System.out::println);
            case "--author":
                bookDao.getByAuthorId(id).forEach(System.out::println);
                break;
            case "--genre":
                bookDao.getByGenreId(id).forEach(System.out::println);
                break;
            case "--delete":
                bookDao.deleteById(id);
                break;
            case "--insert":
                System.out.println("Please insert book name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String bookName = reader.readLine();
                System.out.println("Please insert book author:");
                String authorName = reader.readLine();
                System.out.println("Please insert book genre:");
                String geneName = reader.readLine();
                bookDao.insert(bookName, authorName, geneName);
        }
    }
}
