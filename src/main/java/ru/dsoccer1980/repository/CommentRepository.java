package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer1980.domain.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookId(Long bookId);

    Comment save(Comment comment);
}
