package com.ssafy.enjoy.trip.controller;

import com.ssafy.enjoy.trip.dto.Trip;
import com.ssafy.enjoy.trip.dto.TripCondition;
import com.ssafy.enjoy.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    //여행 생성
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        log.debug("=============================CREATE");
        log.debug(trip.toString());
        int created = tripService.makeTrip(trip);
        log.info("created = {}", created);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    //세션 유저의 전체 여행목록 조회, order column, desc 설정 가능
    @GetMapping
    public ResponseEntity<List<Trip>> readAllUserTrip(TripCondition tripCondition) {
        log.debug("===================READ ALL");
        log.debug(tripCondition.toString());
        List<Trip> tripList = tripService.findTrip(tripCondition);
        return new ResponseEntity<>(tripList, HttpStatus.OK);
    }

    //    tripId에 해당하는 trip 조회여행만 조회 가능
    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> readOneTrip(int tripId) {
        Trip trip = tripService.findOneTrip(tripId);
        return new ResponseEntity<Trip>(trip, HttpStatus.OK);
    }

    //tripId로 세션유저의 trip 삭제
    @DeleteMapping("/{tripId}")
    public ResponseEntity deleteOneTrip(@PathVariable("tripId") int tripId, String userId) {
        log.debug("===================DELETE");
        tripService.deleteOneTrip(userId, tripId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> modify(@RequestBody Trip trip) throws SQLException {
        log.debug("===================MODIFY");
        log.debug(trip.toString());
        if (tripService.modifyTrip(trip) == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
