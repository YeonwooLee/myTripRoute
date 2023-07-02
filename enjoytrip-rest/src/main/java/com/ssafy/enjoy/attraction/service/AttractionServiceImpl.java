package com.ssafy.enjoy.attraction.service;

import com.ssafy.enjoy.attraction.dao.AttractionDao;
import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.attraction.dto.AttractionCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttractionServiceImpl implements AttractionService{
    private final AttractionDao attractionDao;


    @Override
    public Attraction findOneAttraction(int contentId) {
        Attraction attraction = attractionDao.selectAttractionByContentId(contentId);
        return attraction;
    }
}
