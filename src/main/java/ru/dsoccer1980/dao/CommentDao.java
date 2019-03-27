package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Comment;

public interface CommentDao {

    void insert(Comment comment, Long book_id);

    void insert(String content, Long book_id);
}
