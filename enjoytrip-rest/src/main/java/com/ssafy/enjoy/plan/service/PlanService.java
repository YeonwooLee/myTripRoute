package com.ssafy.enjoy.plan.service;

import com.ssafy.enjoy.plan.dto.BigPlan;
import com.ssafy.enjoy.plan.dto.Plan;
import java.util.List;

public interface PlanService {

    List<Plan> findPlanList(int tripId);
    
    Plan findOnePlan(int planId);

    void modify(Plan plan);

    void delete(int planId);

    List<BigPlan> findBigPlanList(int tripId);
  
    int add(Plan plan);
}
