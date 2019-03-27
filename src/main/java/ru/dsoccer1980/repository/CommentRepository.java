package ru.dsoccer1980.repository;

import ru.dsoccer1980.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void insert(Comment comment, Long bookId);

    void insert(String content, Long bookId);

    List<Comment> getByBookId(Long bookId);
}
