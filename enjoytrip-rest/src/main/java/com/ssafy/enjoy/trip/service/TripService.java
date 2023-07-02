package com.ssafy.enjoy.trip.service;

import com.ssafy.enjoy.trip.dto.Trip;
import com.ssafy.enjoy.trip.dto.TripCondition;

import java.util.List;

public interface TripService {
    int makeTrip(Trip trip);

    List<Trip> findTrip(TripCondition tripCondition);

    Trip findOneTrip(int tripId);

    //tripId로 세션 유저의 trip 삭제
    void deleteOneTrip(String userId, int tripId);

    int modifyTrip(Trip trip);
}
