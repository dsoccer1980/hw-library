package ru.dsoccer1980.service;

import java.io.IOException;

public interface GenreActionService {

    void action(String type, Long id) throws IOException;
}
