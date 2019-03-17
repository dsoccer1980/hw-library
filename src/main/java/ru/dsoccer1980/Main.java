package ru.dsoccer1980;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.GenreDao;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class);

        AuthorDao authorDao = applicationContext.getBean(AuthorDao.class);
        System.out.println(authorDao.getAll());
        GenreDao genreDao = applicationContext.getBean(GenreDao.class);
        System.out.println(genreDao.getAll());
    }
}