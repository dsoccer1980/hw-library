package ru.dsoccer1980.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.service.GenreActionService;

import java.io.IOException;


@ShellComponent
public class GenreCommands {

    private final GenreActionService genreActionService;

    public GenreCommands(GenreActionService genreActionService) {
        this.genreActionService = genreActionService;
    }


    @ShellMethod("actions with genres")
    public void genre(
            @ShellOption(defaultValue = "--getAll") String action,
            @ShellOption(defaultValue = "-1") int id) throws IOException {

        genreActionService.action(action, id);



    }

}
