package com.ssafy.enjoy.board.service;

import com.ssafy.enjoy.board.dao.CommentDao;
import com.ssafy.enjoy.board.dto.Comment;
import com.ssafy.enjoy.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    @Override
    public int write(Comment comment) {
        if (StringUtils.isBlank(comment.getComment())) {
            return -1;
        }
        commentDao.insert(comment);
        return comment.getCommentId();
    }

    @Override
    public List<Comment> getComments(String boardId) {
        return commentDao.selectComments(boardId);
    }

    @Override
    public HttpStatus remove(String commentId, String userId) {
        Comment comment = commentDao.selectComment(commentId);
        if (comment == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (!comment.getUserId().equals(userId)) {
            return HttpStatus.BAD_REQUEST;
        }

        if (commentDao.delete(commentId) == 1) {
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }

    @Override
    public HttpStatus modify(Comment comment) {
        Comment target = commentDao.selectComment(String.valueOf(comment.getCommentId()));
        if (target == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (!comment.getUserId().equals(comment.getUserId())) {
            return HttpStatus.BAD_REQUEST;
        }

        if (commentDao.update(comment) == 1) {
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }
}
