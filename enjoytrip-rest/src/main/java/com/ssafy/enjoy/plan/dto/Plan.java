package com.ssafy.enjoy.plan.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Plan {
    private int planId;
    private int tripId;
    private int attractionId;
    private int planDate;
    private int planOrder;
    private int planBudget;
    private String planDesc;
}
