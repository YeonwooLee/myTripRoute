package com.ssafy.enjoy.trip.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Trip {
    private int tripId;
    private String tripName;
    private LocalDateTime createTime;
    private String userId;

}
