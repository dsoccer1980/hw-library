package ru.dsoccer1980.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.dao.GenreDao;


@ShellComponent
public class GenreCommands {

    private final GenreDao genreDao;

    @Autowired
    public GenreCommands(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @ShellMethod("actions with genres")
    public void genre(
            @ShellOption(defaultValue = "--get") String action,
            @ShellOption(defaultValue = "-1") int id) {

        switch (action) {
            case "--get":
                if (id == -1) {
                    genreDao.getAll().forEach(System.out::println);
                } else {
                    System.out.println(genreDao.getById(id));
                }
                break;
            case "--delete":
                genreDao.deleteById(id);
                break;
        }


    }

}
