package ru.dsoccer1980.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.dao.AuthorDao;


@ShellComponent
public class AuthorCommands {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorCommands(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @ShellMethod("show authors")
    public void author(
            @ShellOption(defaultValue = "--get") String action,
            @ShellOption(defaultValue = "-1") int id) {

        switch (action) {
            case "--get":
                if (id == -1) {
                    authorDao.getAll().forEach(System.out::println);
                } else {
                    System.out.println(authorDao.getById(id));
                }
                break;
            case "--delete":
                authorDao.deleteById(id);
                break;
        }


    }

}
