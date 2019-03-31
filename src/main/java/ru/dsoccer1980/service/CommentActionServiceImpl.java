package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Comment;
import ru.dsoccer1980.repository.BookRepository;
import ru.dsoccer1980.repository.CommentRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

@Service
public class CommentActionServiceImpl implements CommentActionService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentActionServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void action(String type, String content, Long book_id) {
        switch (type) {
            case "--insert":
                Book book = bookRepository.findById(book_id).orElseThrow(() -> new NotFoundException("Book not found"));
                commentRepository.save(new Comment(content, book));
                break;
            case "--get":
                System.out.println(commentRepository.findByBookId(book_id));
                break;
        }

    }
}
