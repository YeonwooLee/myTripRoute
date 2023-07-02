package com.ssafy.enjoy.trip.controller;

import com.ssafy.enjoy.trip.dto.Trip;
import com.ssafy.enjoy.trip.dto.TripCondition;
import com.ssafy.enjoy.user.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@Slf4j
class TripControllerTest {
    @Autowired
    TripController tripController;

    protected static MockHttpSession session;
    protected static MockHttpServletRequest request;

    @BeforeAll
    public static void setSession() {
        User user = User.builder().id("admin").build();
        session = new MockHttpSession();
        session.setAttribute("userinfo", user);

        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void 여행생성() {
        String original = "hello";
        String tripName = "테스트용여행명";
        int count = 3;
        String repeated = IntStream.range(0, count)
                .mapToObj(i -> original)
                .collect(Collectors.joining());
        System.out.println(repeated); // output: "hellohellohello"
        //when
        Trip create = tripController.createTrip(session,tripName).getBody();//여행 생성
        log.info("create = {}", create);
        //TODO assert 추가
    }

    @Test
    public void 유저의여행목록조회() {
        //given
        String userId = "admin";
        TripCondition tripCondition = TripCondition.builder().userId(userId).build();

        List<Trip> tripList = tripController.readAllUserTrip(session, tripCondition).getBody();
        log.info("tripList = {}", tripList);

        int wholeCnt = tripList.size();//가져온 리스트 크기
        int cnt = 0;
        //when
        for(Trip trip:tripList){
            if(trip.getUserId().equals(userId)) cnt++;
        }
        //then
        assertEquals(wholeCnt,cnt);
    }
    @Test
    public void tripId로_trip조회(){
        //given
        int tripId = 6;


        //when
        Trip trip = tripController.readOneTrip(tripId).getBody();

        //then
        assertEquals(trip.getTripId(),6);
    }
    @Test
    public void 유저의여행삭제(){
        //given

        //when
        tripController.deleteOneTrip(session,5);

        //then
    }




//    @Test
//    public void 여행수정(){
//        //given
//
//        //when
//
//        //then
//    }
//    @Test
//    public void 여행삭제(){
//        //given
//
//        //when
//
//        //then
//    }

}
