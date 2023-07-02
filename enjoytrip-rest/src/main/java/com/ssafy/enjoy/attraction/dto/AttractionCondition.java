package com.ssafy.enjoy.attraction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionCondition {
    int sidoCode;
    int gugunCode;
    String keyword;
    int contentTypeId;
}
