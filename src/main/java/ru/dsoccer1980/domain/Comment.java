package ru.dsoccer1980.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "content")
    @NotBlank
    @Size(min = 2, max = 150)
    @Getter
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @Setter
    @NotNull
    private Book book;

    public Comment(long id, String content) {
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
