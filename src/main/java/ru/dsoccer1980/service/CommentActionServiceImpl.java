package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.dao.CommentDao;
;
import java.io.IOException;

@Service
public class CommentActionServiceImpl implements CommentActionService {

    private CommentDao commentDao;

    public CommentActionServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    @Override
    public void action(String type, String content, Long book_id) throws IOException {
        switch (type) {
            case "--insert":
                commentDao.insert(content, book_id);
                break;
        }

    }
}
