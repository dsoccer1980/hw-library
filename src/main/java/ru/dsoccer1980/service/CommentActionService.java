package ru.dsoccer1980.service;

import java.io.IOException;

public interface CommentActionService {

    void action(String type, String content, Long user_id) throws IOException;
}
