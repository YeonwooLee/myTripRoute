package com.ssafy.enjoy.plan.controller;

import com.ssafy.enjoy.plan.dto.Plan;
import com.ssafy.enjoy.trip.controller.TripController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@Slf4j
class PlanControllerTest {
    @Autowired
    PlanController planController;

    @Test
    public void tripId로_plan_list_조회(){
        //given

        //when
        List<Plan> planList = planController.getPlanList(2).getBody();
        //then
//        log.info("planList is {}",planList);
        planList.forEach(p -> {
            log.info("{}",p);
        });


    }

    @Test
    public void planDetail조회(){
        //given
        int planId =3;
        //when
        Plan body = planController.getOnePlan(planId).getBody();


        //then
        assertEquals(body.getPlanId(),planId);
    }

    @Test
    public void plan수정(){
        //given
        int planId = 3;
        Plan plan = Plan.builder().planId(planId).planDesc("수정됨").build();
        //when
        Integer id = planController.modifyPlan(planId, plan).getBody();
        Plan res = planController.getOnePlan(id).getBody();
        log.info("res = {}",res);

        //then

    }

    @Test
    public void plan_삭제(){
        //given
        int planId = 4;

        //when
        planController.deletePlan(planId);
        
        //then
        Plan res = planController.getOnePlan(planId).getBody();
        assertNull(res);
    }




}
