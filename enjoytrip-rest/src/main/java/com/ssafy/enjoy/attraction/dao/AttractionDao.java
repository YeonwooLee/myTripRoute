package com.ssafy.enjoy.attraction.dao;

import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.attraction.dto.AttractionCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionDao {

    //contentId로 관광지 조회
    Attraction selectAttractionByContentId(int contentId);
}
