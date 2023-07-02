package com.ssafy.enjoy.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private int commentId;
    private String userId;
    private String comment;
    private LocalDateTime memoTime;
    private int boardId;
}
