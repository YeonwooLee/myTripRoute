package com.ssafy.enjoy.board.dao;

import com.ssafy.enjoy.board.dto.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {
    int insert(Comment comment);

    List<Comment> selectComments(String boardId);

    Comment selectComment(String commentId);

    int delete(String commentId);

    int update(Comment comment);
}
