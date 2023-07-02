package com.ssafy.enjoy.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private int no;
    private String name;
    private String id;
    private String pw;
    private String email;
    private Grade grade;
}
