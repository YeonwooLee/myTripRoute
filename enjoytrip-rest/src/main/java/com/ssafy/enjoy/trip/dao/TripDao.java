package com.ssafy.enjoy.trip.dao;

import com.ssafy.enjoy.trip.dto.Trip;
import com.ssafy.enjoy.trip.dto.TripCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TripDao {
    //새 trip 삽입
    int insertTrip(Trip trip);

    //tripCondition으로 trip 조회
    List<Trip> selectTrip(TripCondition tripCondition);

    //tripId에 attractionId를 plan으로 추가함
    int insertPlan(Map<String, Object> map);

    Trip selectOneTrip(int tripId);

    int updateTrip(Trip trip);

    void deleteOneTrip(Map<String, String> map);
}
