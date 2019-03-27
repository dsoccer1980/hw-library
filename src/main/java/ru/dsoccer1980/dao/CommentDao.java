package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Comment;

import java.util.List;

public interface CommentDao {

    void insert(Comment comment, Long bookId);

    void insert(String content, Long bookId);

    List<Comment> getByBookId(Long bookId);
}
