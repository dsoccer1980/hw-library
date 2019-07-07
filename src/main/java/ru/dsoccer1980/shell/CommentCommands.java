package ru.dsoccer1980.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dsoccer1980.service.CommentActionService;

import java.io.IOException;


@ShellComponent
public class CommentCommands {

    private final CommentActionService commentActionService;

    public CommentCommands(CommentActionService commentActionService) {
        this.commentActionService = commentActionService;
    }

    @ShellMethod("actions with comments")
    public void comment(
            @ShellOption String action,
            @ShellOption(defaultValue = "") String content,
            @ShellOption String book_id) throws IOException {

        commentActionService.action(action, content, book_id);
    }
}

