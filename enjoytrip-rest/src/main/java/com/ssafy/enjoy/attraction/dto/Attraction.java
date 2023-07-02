package com.ssafy.enjoy.attraction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attraction {
    private int contentId;
    private String homepage;
    private String overview;
    private String telname;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readcount;
    private int sidoCode;
    private int gugunCode;
    private float latitude;
    private float longitude;
    private String mlevel;
}
