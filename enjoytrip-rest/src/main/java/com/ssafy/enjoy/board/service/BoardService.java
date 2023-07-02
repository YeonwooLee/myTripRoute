package com.ssafy.enjoy.board.service;

import com.ssafy.enjoy.board.dto.Board;
import com.ssafy.enjoy.board.dto.BoardCondition;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    int write(Board board);

    List<Board> searchByCondition(BoardCondition condition);

    Board getDetail(String id);

    HttpStatus remove(String boardId, String userId) throws SQLException;

    HttpStatus modify(Board board) throws SQLException;

    int updatehit(String id);
}
