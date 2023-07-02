package com.ssafy.enjoy.board.service;

import com.ssafy.enjoy.board.dto.Comment;
import java.util.List;
import org.springframework.http.HttpStatus;

public interface CommentService {
    int write(Comment comment);

    List<Comment> getComments(String boardId);

    HttpStatus remove(String commentId, String userId);

    HttpStatus modify(Comment comment);
}
