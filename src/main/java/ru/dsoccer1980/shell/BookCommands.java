package ru.dsoccer1980.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.GenreDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@ShellComponent
public class BookCommands {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public BookCommands(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod("actions with books")
    public void book(
            @ShellOption(defaultValue = "--get") String action,
            @ShellOption(defaultValue = "-1") int id) throws IOException {

        switch (action) {
            case "--get":
                if (id == -1) {
                    bookDao.getAll().forEach(System.out::println);
                } else {
                    System.out.println(bookDao.getById(id));
                }
                break;
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
