package com.ssafy.enjoy.board.service;

import com.ssafy.enjoy.board.dao.BoardDao;
import com.ssafy.enjoy.board.dto.Board;
import com.ssafy.enjoy.board.dto.BoardCondition;
import com.ssafy.enjoy.board.dto.Category;
import com.ssafy.enjoy.user.dao.UserDao;
import com.ssafy.enjoy.user.dto.Grade;
import com.ssafy.enjoy.user.dto.User;
import com.ssafy.enjoy.util.StringUtils;
import java.sql.SQLException;
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
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final UserDao userDao;

    @Override
    public int write(Board board) {
        if (StringUtils.isBlank(board.getUserId())) {
            return -1;
        }
        if (StringUtils.isBlank(board.getSubject())) {
            return -1;
        }

        if (board.getCategory() == null) {
            board.setCategory(Category.FREE);
        }
        
        board.setHit(0);
        return boardDao.insert(board);
    }

    @Override
    public List<Board> searchByCondition(BoardCondition condition) {
        condition.setDefault();
        log.debug(condition.toString());
        return boardDao.selectByCondition(condition);
    }

    @Override
    public Board getDetail(String id) {
        return boardDao.selectOne(id);
    }

    @Override
    public HttpStatus remove(String boardId, String userId) throws SQLException {
        Board board = boardDao.selectOne(boardId);
        if (board == null) {
            return HttpStatus.BAD_REQUEST;
        }

        User user = userDao.userInfo(userId);
        if (user == null) {
            return HttpStatus.BAD_REQUEST;
        }

        log.debug(user.toString());

        if (user.getGrade() == Grade.ADMIN || board.getUserId().equals(userId)) {
            boardDao.delete(boardId);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public HttpStatus modify(Board board) throws SQLException {
        int boardId = board.getBoardId();
        if (boardDao.selectOne(String.valueOf(boardId)) == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (userDao.userInfo(board.getUserId()) == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (boardDao.update(board) == 1) {
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }

    @Override
    public int updatehit(String id) {
        return boardDao.addHit(id);
    }
}
