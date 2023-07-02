package com.ssafy.enjoy.trip.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TripCondition {
    String userId;
    String orderBy;
    String orderDir;
}
