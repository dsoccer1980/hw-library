package ru.dsoccer1980.domain;

public class Book {
    private int id;
    private String name;
    private int author_id;
    private int genre_id;

    public Book(int id, String name, int author_id, int genre_id) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author_id=" + author_id +
                ", genre_id=" + genre_id +
                '}';
    }
}
