package com.ssafy.enjoy.trip.service;

import com.ssafy.enjoy.trip.dao.TripDao;
import com.ssafy.enjoy.trip.dto.Trip;
import com.ssafy.enjoy.trip.dto.TripCondition;
import com.ssafy.enjoy.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripServiceImpl implements TripService {
    private final TripDao tripDao;

    //여행 생성
    @Override
    public int makeTrip(Trip trip) {
        int inserted = tripDao.insertTrip(trip);
        return inserted;
    }

    //여행 조회
    @Override
    public List<Trip> findTrip(TripCondition tripCondition) {
        //정렬기준 없으면 생성일 기준
        if (StringUtils.isBlank(tripCondition.getOrderBy())) {
            tripCondition.setOrderBy("create_time");
        }
        //정렬방식 없으면 오래된 것부터 출력(asc)
        if (StringUtils.isBlank(tripCondition.getOrderDir())) {
            tripCondition.setOrderDir("asc");
        }

        List<Trip> tripList = tripDao.selectTrip(tripCondition);
        return tripList;
    }

    @Override
    public Trip findOneTrip(int tripId) {
        Trip trip = tripDao.selectOneTrip(tripId);
        return trip;
    }

    @Override
    public void deleteOneTrip(String userId, int tripId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("tripId", Integer.toString(tripId));

        tripDao.deleteOneTrip(map);
    }

    @Override
    public int modifyTrip(Trip trip) {
        return tripDao.updateTrip(trip);
    }
}
