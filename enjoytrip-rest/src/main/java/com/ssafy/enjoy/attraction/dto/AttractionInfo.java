package com.ssafy.enjoy.attraction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionInfo {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String first_image;
    private String first_image2;
    private int readcount;
    private int sidoCode;
    private int gugunCode;
    private float latitude;
    private float longitude;
    private String mlevel;

}
