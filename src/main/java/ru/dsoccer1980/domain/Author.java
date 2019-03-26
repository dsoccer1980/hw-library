package ru.dsoccer1980.domain;

public class Author {
    private long id;
    private String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + id + ", '" + name + '\'' + '}';
    }
}
