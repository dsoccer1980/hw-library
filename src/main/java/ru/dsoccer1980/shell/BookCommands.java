package ru.dsoccer1980.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.service.BookActionService;

import java.io.IOException;


@ShellComponent
public class BookCommands {

    private final BookActionService bookActionService;

    public BookCommands(BookActionService bookActionService) {
        this.bookActionService = bookActionService;
    }

    @ShellMethod("actions with books")
    public void book(
            @ShellOption(defaultValue = "--getAll") String action,
            @ShellOption(defaultValue = "-1") int id) throws IOException {

        bookActionService.action(action, id);
    }

}
