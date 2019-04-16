package ru.dsoccer1980.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Document
public class Comment {
    @Id
    @Getter
    private String id;

    @NotBlank
    @Size(min = 2, max = 150)
    @Getter
    private String content;

    @Setter
    @NotNull
    private Book book;

    public Comment(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }

    @Override
    public String toString() {
        return "{" + id + ", '" + content + '\'' + '}';
    }

}
