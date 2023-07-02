package com.ssafy.enjoy.attraction.dao;

import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.attraction.dto.AttractionCondition;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AttractionDaoTest {
    @Autowired
    AttractionDao attractionDao;

    @Test
    void 모든_필터_사용한_검색(){
        AttractionCondition condition = new AttractionCondition();
        String keyword = "칼국수";
        int sidoCode = 31;
        int gugunCode = 29;
        int contentTypeId = 29;

        condition.setKeyword(keyword);
        condition.setSidoCode(sidoCode);
        condition.setGugunCode(gugunCode);
        condition.setContentTypeId(contentTypeId);
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);

        int count = 0;//필터와 일치하는 행
        for(Attraction attr:attractionList){
            if(attr.getTitle().contains(keyword)
                    && attr.getSidoCode()==sidoCode
                    && attr.getGugunCode()==gugunCode
                    && attr.getContentTypeId()==contentTypeId
            ) count++;
        }
        log.info("count = {}",count);
        assertEquals(count,attractionList.size());
    }

    @Test
    void 키워드로_검색() {
        AttractionCondition condition = new AttractionCondition();
        String keyword = "칼국수";

        condition.setKeyword(keyword);
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);

        int count = 0;//칼국수가 제목에 포함된 행
        for(Attraction attr:attractionList){
            if(attr.getTitle().contains(keyword)) count++;
        }

        log.info("count = {}",count);
        assertEquals(count,attractionList.size());
    }

    @Test
    void 시도코드로_검색() {
        AttractionCondition condition = new AttractionCondition();
        int sidoCode = 31;

        condition.setSidoCode(sidoCode);
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);

        int count = 0;//시도코드가 sidoCode인 행
        for(Attraction attr:attractionList){
            if(attr.getSidoCode()==sidoCode) count++;
        }

//        Assertions.assertEquals(count,attractionList.size());
        log.info("count = {}",count);
        assertEquals(count,attractionList.size());
    }
    @Test
    void 구군코로_검색() {
        AttractionCondition condition = new AttractionCondition();
        int gugunCode = 29;

        condition.setGugunCode(gugunCode);
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);

        int count = 0;//시도코드가 gugunCode 행
        for(Attraction attr:attractionList){
            if(attr.getGugunCode()==gugunCode) count++;
        }

//        Assertions.assertEquals(count,attractionList.size());
        log.info("count = {}",count);
        assertEquals(count,attractionList.size());
    }
    @Test
    void content_type_id로_검색() {
        AttractionCondition condition = new AttractionCondition();
        int contentTypeId = 12;

        condition.setContentTypeId(contentTypeId);
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);

        int count = 0;//content_type_id가 contentTypeId인 행
        for(Attraction attr:attractionList){
            if(attr.getContentTypeId()==contentTypeId) count++;
        }

//        Assertions.assertEquals(count,attractionList.size());
        log.info("count = {}",count);
        assertEquals(count,attractionList.size());
    }

    @Test
    void 필터없이_검색() {
        AttractionCondition condition = new AttractionCondition();
        List<Attraction> attractionList = attractionDao.selectAttraction(condition);
        int attractionCnt = attractionDao.countAllAttraction();

        log.info("필터없이 검색한 리스트의 크기 = {}", attractionList.size());
        log.info("정답 = {}", attractionCnt);

        assertEquals(attractionCnt,attractionList.size());
    }
}
