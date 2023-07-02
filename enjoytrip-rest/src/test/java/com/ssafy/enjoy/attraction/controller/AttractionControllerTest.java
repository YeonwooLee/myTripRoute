package com.ssafy.enjoy.attraction.controller;

import com.ssafy.enjoy.attraction.dto.Attraction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class AttractionControllerTest {
    @Autowired
    AttractionController attractionController;

    @Test
    void 관광지조회_존재하는ID() {
        int attractionId = 125266;
        String attractionOverview = "해발 1,200m의 청태산을 주봉으로 하여 인공림과 천연림이 잘 조화된 울창한 산림을 바탕으로 한 국유림 경영 시범단지로서 숲속에는 온갖 야생 동식물이 고루 서식하고 있어 자연박물관을 찾은 기분을 느낄 수 있다. 영동고속도로 신갈기점 강릉방향 128km 지점에 위치하고 있어 여름철 동해안 피서객들이 잠시 쉬었다 가기에 편리하고, 청소년의 심신수련을 위한 숲속교실도 설치되어 있으며 울창한 잣나무 숲속의 산림욕장은 한번왔다간 사람은 누구나 매료되어 찾는 곳이기도 하다. * 구역면적 - 403 ha";

        Attraction attraction = attractionController.searchAttractionDetail(attractionId).getBody();
        log.info("is {}",attraction);
        assertEquals(attractionOverview,attraction.getOverview());
    }

    @Test
    void 관광지조회_없는ID() {
        int attractionId = -1;

        Attraction attraction = attractionController.searchAttractionDetail(attractionId).getBody();
        assertNull(attraction);
    }
}
