package ru.dsoccer1980.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.service.AuthorActionService;

import java.io.IOException;


@ShellComponent
public class AuthorCommands {

    private final AuthorActionService authorActionService;

    public AuthorCommands(AuthorActionService authorActionService) {
        this.authorActionService = authorActionService;
    }

    @ShellMethod("actions with authors")
    public void author(
            @ShellOption(defaultValue = "--getAll") String action,
            @ShellOption(defaultValue = "-1") long id) throws IOException {

        authorActionService.action(action, id);
    }

}

