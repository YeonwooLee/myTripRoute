package com.ssafy.enjoy.plan.dao;

import com.ssafy.enjoy.plan.dto.Plan;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanDao {

    //tripId로 plan 목록 조회
    List<Plan> selectPlanList(int tripId);

    Plan selectPlan(int planId);

    //tripId에 attractionId를 plan으로 추가함
    int insertPlan(Plan plan);

    //
    void updatePlan(Plan plan);

    void deletePlan(int planId);
}
