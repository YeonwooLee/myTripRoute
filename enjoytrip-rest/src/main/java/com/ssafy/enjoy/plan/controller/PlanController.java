package com.ssafy.enjoy.plan.controller;

import com.ssafy.enjoy.plan.dto.BigPlan;
import com.ssafy.enjoy.plan.dto.Plan;
import com.ssafy.enjoy.plan.service.PlanService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    //trip id에 해당하는 planList
    @GetMapping("/{tripId}")
    public ResponseEntity<List<Plan>> getPlanList(@PathVariable("tripId") int tripId) {
        List<Plan> planList = planService.findPlanList(tripId);

        return new ResponseEntity<>(planList, HttpStatus.OK);
    }

    //trip id에 해당하는 planList-관광지정보 추가된 버전
    @GetMapping("biglist/{tripId}")
    public ResponseEntity<List<BigPlan>> getBigPlanList(@PathVariable("tripId") int tripId){
        List<BigPlan> bigPlanList = planService.findBigPlanList(tripId);
        return new ResponseEntity<>(bigPlanList, HttpStatus.OK);
    }
  
    //plan detail Plan
    @GetMapping("/detail/{planId}")
    public ResponseEntity<Plan> getOnePlan(@PathVariable("planId")int planId) {
        Plan plan = planService.findOnePlan(planId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    //plan update
    @PatchMapping("/{planId}")
    public ResponseEntity<Integer> modifyPlan(int planId, @RequestBody Plan plan) {
        planService.modify(plan);
        return new ResponseEntity<>(planId, HttpStatus.OK);

    }

    //plan delete
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable("planId") int planId) {
        log.debug("===================DELETE");
        log.debug("planId: {}", planId);
        planService.delete(planId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPlan(@RequestBody Plan plan) {
        log.debug("=============ADD PLAN");
        log.debug("Plan : {}", plan.toString());
        if (planService.add(plan) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
