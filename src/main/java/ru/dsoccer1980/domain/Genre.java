package ru.dsoccer1980.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
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
