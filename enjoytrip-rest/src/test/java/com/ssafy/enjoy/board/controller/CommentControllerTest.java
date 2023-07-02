package com.ssafy.enjoy.board.controller;

import com.ssafy.enjoy.board.dao.BoardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
class CommentControllerTest {
    
    @Autowired
    BoardController boardController;

    @Autowired
    BoardDao boardDao;

}
