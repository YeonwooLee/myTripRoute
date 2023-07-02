package com.ssafy.enjoy.board.dao;

import com.ssafy.enjoy.board.dto.Board;
import com.ssafy.enjoy.board.dto.BoardCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    int insert(Board board);

    List<Board> selectByCondition(BoardCondition condition);

    Board selectOne(String id);

    int delete(String id);

    int update(Board board);

    int addHit(String id);
}
