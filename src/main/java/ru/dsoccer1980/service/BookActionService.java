package ru.dsoccer1980.service;

import java.io.IOException;

public interface BookActionService {

    void action(String type, String id) throws IOException;
}
