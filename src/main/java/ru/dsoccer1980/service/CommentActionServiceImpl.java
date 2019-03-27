package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.repository.CommentRepository;
import java.io.IOException;

@Service
public class CommentActionServiceImpl implements CommentActionService {

    private CommentRepository commentRepository;

    public CommentActionServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void action(String type, String content, Long book_id) throws IOException {
        switch (type) {
            case "--insert":
                commentRepository.insert(content, book_id);
                break;
            case "--get":
                System.out.println(commentRepository.getByBookId(book_id));
                break;
        }

    }
}
