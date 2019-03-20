package ru.dsoccer1980.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.dao.BookDao;


@ShellComponent
public class BookCommands {

    private final BookDao bookDao;

    @Autowired
    public BookCommands(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ShellMethod("show books")
    public void book(
            @ShellOption(defaultValue = "--get") String action,
            @ShellOption(defaultValue = "-1") int id) {

        switch (action) {
            case "--get":
                if (id == -1) {
                    bookDao.getAll().forEach(System.out::println);
                } else {
                    System.out.println(bookDao.getById(id));
                }
                break;
            case "--delete":
                bookDao.deleteById(id);
                break;
        }


    }

}
