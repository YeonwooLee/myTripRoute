package com.ssafy.enjoy.attraction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionDescription {
    private int contentId;
    private String homepage;
    private String overview;
    private String telname;
}
