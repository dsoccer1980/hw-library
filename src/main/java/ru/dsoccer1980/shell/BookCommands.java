package ru.dsoccer1980.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.GenreDao;


@ShellComponent
public class BookCommands {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Autowired
    public BookCommands(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @ShellMethod("show books")
    public void book(
            @ShellOption int id
    ) {
        // invoke service

        if (id == 0) {
            System.out.println(bookDao.getAll());
        } else {
            System.out.println(bookDao.getById(id));
        }

    }

}
