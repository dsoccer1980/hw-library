package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Comment;
import ru.dsoccer1980.repository.CommentRepository;

@Service
public class CommentActionServiceImpl implements CommentActionService {

    private CommentRepository commentRepository;

    public CommentActionServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void action(String type, String content, Long book_id) {
        switch (type) {
            case "--insert":
                commentRepository.insert(new Comment(content), book_id);
                break;
            case "--get":
                System.out.println(commentRepository.getByBookId(book_id));
                break;
        }

    }
}
