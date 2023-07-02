package com.ssafy.enjoy.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Board {
    private int boardId;
    private String userId;
    private String subject;
    private String content;
    private int hit;
    private LocalDateTime registerTime;
    private Category category;
}
