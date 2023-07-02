package com.ssafy.enjoy.board.controller;

import com.ssafy.enjoy.board.dao.BoardDao;
import com.ssafy.enjoy.board.dto.Board;
import com.ssafy.enjoy.board.dto.BoardCondition;
import com.ssafy.enjoy.board.dto.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
@Transactional
class BoardControllerTest {

    @Autowired
    BoardController boardController;

    @Autowired
    BoardDao boardDao;

    @BeforeEach
    void insert_temp_data() {
        log.info("=========================INSERT TEMP DATA=========================");
        LocalDateTime now = LocalDateTime.now();
        for (int i = 1; i <= 100; i++) {
            Board board = new Board();

            board.setUserId("ssafy");
            if (i % 3 == 0) {
                board.setUserId("admin");
            }

            board.setSubject("test #" + i);

            String original = "hello";
            String repeated = IntStream.range(0, i)
                    .mapToObj(j -> original)
                    .collect(Collectors.joining());
            board.setContent(repeated);

            board.setHit(i);

            board.setRegisterTime(now.plusDays(i));

            if (i % 10 == 0) {
                board.setUserId("admin");
                board.setCategory(Category.NOTICE);
            } else {
                board.setCategory(Category.NORMAL);
            }

            boardDao.insert(board);
        }
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void write_success() throws Exception {
        Board board = Board.builder().userId("ssafy").subject("test article").content("test").build();
        assertSame(boardController.write(board).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("게시글 등록 실패 - 유저 없음")
    void write_fail_user() throws Exception {
        Board board = Board.builder().subject("test article").content("test").build();
        assertSame(boardController.write(board).getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    @DisplayName("게시글 등록 실패 - 제목 없음")
    void write_fail_subject() throws Exception {
        Board board = Board.builder().userId("ssafy").content("test").build();
        assertSame(boardController.write(board).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("게시글 등록 실패 - 존재하지 않는 유저")
    void write_fail_user_not_exist() throws Exception {
        Board board = Board.builder().userId("test").subject("test article").content("test").build();
        assertSame(boardController.write(board).getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void search_all() {
        BoardCondition boardCondition = new BoardCondition();
        assertSame(boardController.searchBy(boardCondition).getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("게시글 검색 - userId")
    void search_by_user_id() {
        String userid = "ssafy";

        BoardCondition boardCondition = new BoardCondition();
        boardCondition.setUserId(userid);

        ResponseEntity<?> response = boardController.searchBy(boardCondition);
        assertSame(response.getStatusCode(), HttpStatus.OK);

        for (Board board : (List<Board>) response.getBody()) {
            assertTrue(board.getUserId().equals(userid));
        }
    }

    @Test
    @DisplayName("게시글 검색 - keyword")
    void search_by_keyword() {
        String keyword = "test";

        BoardCondition boardCondition = new BoardCondition();
        boardCondition.setKeyword(keyword);

        ResponseEntity<?> response = boardController.searchBy(boardCondition);
        assertSame(response.getStatusCode(), HttpStatus.OK);

        for (Board board : (List<Board>) response.getBody()) {
            assertTrue(board.getSubject().contains(keyword));
        }
    }

    @Test
    @DisplayName("게시글 검색 - 카테고리")
    void search_by_category() {
        Category category = Category.NOTICE;

        BoardCondition boardCondition = new BoardCondition();
        boardCondition.setCategory(category);

        ResponseEntity<?> response = boardController.searchBy(boardCondition);
        assertSame(response.getStatusCode(), HttpStatus.OK);

        for (Board board : (List<Board>) response.getBody()) {
            assertTrue(board.getCategory().equals(category));
        }
    }

    @Test
    @DisplayName("게시글 상세 조회 성공")
    void detail_success() {
        assertSame(boardController.detail("100").getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("게시글 상세 조회 실패")
    void detail_fail() {
        assertSame(boardController.detail("0").getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("게시글 삭제 성공")
    void delete_success() throws SQLException {
        assertSame(boardController.remove("22", "ssafy").getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("게시글 삭제 실패 - 존재하지 않는 게시글")
    void delete_fail_not_exist() throws SQLException {
        assertSame(boardController.remove("0", "ssafy").getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("게시글 삭제 실패 - 다른 사용자 글")
    void delete_fail_different_user() throws SQLException {
        assertSame(boardController.remove("22", "testid").getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    @DisplayName("게시글 삭제 성공 - 관리자")
    void delete_success_admin() throws SQLException {
        assertSame(boardController.remove("22", "admin").getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("게시글 수정 - 성공")
    void update_board_success() throws SQLException {
        String modifiedContent = "hello test";
        // Board(boardId=22, userId=ssafy, subject=test #1, content=hello, hit=1, registerTime=2023-05-04T13:46:12, category=NORMAL)
        Board board = Board.builder().boardId(22).userId("ssafy").content(modifiedContent).build();
        ResponseEntity<?> responseEntity = boardController.modify("22", board);
        String boardId = responseEntity.getBody().toString();
        Board result = (Board) (boardController.detail(boardId).getBody());
        assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
        assertTrue(result.getContent().equals(modifiedContent));
    }

    @Test
    @DisplayName("게시글 수정 - 존재하지 않는 글")
    void update_board_fail_board_not_exist() throws SQLException {
        String modifiedContent = "hello test";
        // Board(boardId=22, userId=ssafy, subject=test #1, content=hello, hit=1, registerTime=2023-05-04T13:46:12, category=NORMAL)
        Board board = Board.builder().boardId(12340).userId("ssafy").content(modifiedContent).build();
        ResponseEntity<?> responseEntity = boardController.modify("22", board);
        assertSame(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("게시글 수정 - 존재하지 않는 사용자")
    void update_board_fail_user_not_exist() throws SQLException {
        String modifiedContent = "hello test";
        // Board(boardId=22, userId=ssafy, subject=test #1, content=hello, hit=1, registerTime=2023-05-04T13:46:12, category=NORMAL)
        Board board = Board.builder().boardId(22).userId("sss").content(modifiedContent).build();
        ResponseEntity<?> responseEntity = boardController.modify("22", board);
        assertSame(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("게시글 수정 - 다른 사람 글")
    void update_board_fail_user() throws SQLException {
        String modifiedContent = "hello test";
        // Board(boardId=22, userId=ssafy, subject=test #1, content=hello, hit=1, registerTime=2023-05-04T13:46:12, category=NORMAL)
        Board board = Board.builder().boardId(22).userId("admin").content(modifiedContent).build();
        ResponseEntity<?> responseEntity = boardController.modify("22", board);
        assertSame(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
