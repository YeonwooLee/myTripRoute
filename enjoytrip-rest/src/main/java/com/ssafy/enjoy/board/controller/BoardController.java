package com.ssafy.enjoy.board.controller;

import com.ssafy.enjoy.board.dto.Board;
import com.ssafy.enjoy.board.dto.BoardCondition;
import com.ssafy.enjoy.board.service.BoardService;
import com.ssafy.enjoy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> write(@RequestBody Board board) throws Exception {
        if (userService.userInfo(board.getUserId()) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int response = boardService.write(board);
        if (response == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(board.getBoardId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String id) {
        Board board = boardService.getDetail(id);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.debug(board.toString());
        boardService.updatehit(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> searchBy(BoardCondition condition) {
        log.debug(condition.toString());
        List<Board> boardList = boardService.searchByCondition(condition);
        if (boardList == null || boardList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.debug("boardList.size() : {}", boardList.size());
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") String boardId, String userId) throws SQLException {
        return new ResponseEntity<>(boardService.remove(boardId, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable("id") String boardId, @RequestBody Board board) throws SQLException {
        HttpStatus httpStatus = boardService.modify(board);
        if (httpStatus == HttpStatus.OK) {
            return new ResponseEntity<>(boardId, httpStatus);
        }
        return new ResponseEntity<>(httpStatus);
    }
}
