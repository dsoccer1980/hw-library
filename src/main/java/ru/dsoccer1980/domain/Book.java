package ru.dsoccer1980.domain;

public class Book {
    private long id;
    private String name;
    private Author author;
    private Genre genre;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                id +
                ", '" + name + '\'' +
                ", " + author +
                ", " + genre +
                '}';
    }
}
