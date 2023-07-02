package com.ssafy.enjoy.board.controller;

import com.ssafy.enjoy.board.dto.Comment;
import com.ssafy.enjoy.board.service.CommentService;
import com.ssafy.enjoy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> write(@RequestBody Comment comment) throws Exception {
        if (userService.userInfo(comment.getUserId()) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        int newCommentId = commentService.write(comment);
        log.debug("new comment id : {}", newCommentId);
        return new ResponseEntity<>(newCommentId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable("id") String boardId) {
        List<Comment> comments = commentService.getComments(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> remove(String commentId, String userId) {
        log.debug("================DELETE================");
        log.debug("comment: {}", commentId);
        log.debug("user: {}", userId);
        HttpStatus status = commentService.remove(commentId, userId);
        return new ResponseEntity<>(status);
    }

    @PatchMapping
    public ResponseEntity<?> modify(@RequestBody Comment comment) {
        HttpStatus status = commentService.modify(comment);
        return new ResponseEntity<>(comment.getCommentId(), status);
    }
}
