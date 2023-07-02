package com.ssafy.enjoy.plan.service;

import com.ssafy.enjoy.attraction.dao.AttractionDao;
import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.plan.dao.PlanDao;
import com.ssafy.enjoy.plan.dto.BigPlan;
import com.ssafy.enjoy.plan.dto.Plan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {
    private final PlanDao planDao;
    private final AttractionDao attractionDao;

    //여행에 포함된 목록 조회
    @Override
    public List<Plan> findPlanList(int tripId) {
        List<Plan> planList = planDao.selectPlanList(tripId);
        return planList;
    }

    @Override
    public List<BigPlan> findBigPlanList(int tripId) {
        List<BigPlan> bigPlanList = new ArrayList<>();
        List<Plan> planList = planDao.selectPlanList(tripId);

        for(Plan p:planList){
            BigPlan bp =  new BigPlan();
            bp.getPlanAttr(p);
            Attraction attraction = attractionDao.selectAttractionByContentId(p.getAttractionId());
            log.info("attraciton {}",attraction);
            bp.setAttractionTitle(attraction.getTitle());
            bp.setLatitude(attraction.getLatitude());
            bp.setLongitude(attraction.getLongitude());
            bp.setFirstImg(attraction.getFirstImage());
            bigPlanList.add(bp);
        }
        return bigPlanList;
    }
    @Override
    public Plan findOnePlan(int planId) {
        Plan plan = planDao.selectPlan(planId);
        return plan;
    }

    @Override
    public void modify(Plan plan) {
        planDao.updatePlan(plan);
    }

    @Override
    public void delete(int planId) {
        planDao.deletePlan(planId);
    }

    //여행에 일정 추가
    @Override
    public int add(Plan plan) {
        return planDao.insertPlan(plan);
    }
}
