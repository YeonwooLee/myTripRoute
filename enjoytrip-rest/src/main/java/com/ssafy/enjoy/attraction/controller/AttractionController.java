package com.ssafy.enjoy.attraction.controller;


import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.attraction.dto.AttractionCondition;
import com.ssafy.enjoy.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
@Slf4j
public class AttractionController {
    private final AttractionService attractionService;

    //관광지
    @GetMapping("/{contentId}")
    public ResponseEntity<Attraction> searchAttractionDetail(@PathVariable("contentId") int contentId) {
        Attraction attraction = attractionService.findOneAttraction(contentId);
        log.info("찾은 관과지 = {}",attraction);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }
}

