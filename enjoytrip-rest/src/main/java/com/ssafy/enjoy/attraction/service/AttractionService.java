package com.ssafy.enjoy.attraction.service;

import com.ssafy.enjoy.attraction.dto.Attraction;
import com.ssafy.enjoy.attraction.dto.AttractionCondition;

import java.util.List;

public interface AttractionService {
    Attraction findOneAttraction(int id);
}
