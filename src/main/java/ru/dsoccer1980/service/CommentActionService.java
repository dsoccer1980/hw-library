package ru.dsoccer1980.service;

import java.io.IOException;

public interface CommentActionService {

    void action(String type, String content, String user_id) throws IOException;
}
