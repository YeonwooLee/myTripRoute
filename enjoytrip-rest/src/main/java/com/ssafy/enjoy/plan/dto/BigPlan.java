package com.ssafy.enjoy.plan.dto;

import lombok.*;



public class BigPlan extends Plan{
    String firstImg;
    String attractionTitle;
    double longitude;
    double latitude;

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }


    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void getPlanAttr(Plan plan){
        this.setPlanId(plan.getPlanId());
        this.setTripId(plan.getTripId());
        this.setAttractionId(plan.getAttractionId());
        this.setPlanDate(plan.getPlanDate());
        this.setPlanOrder(plan.getPlanOrder());
        this.setPlanBudget(plan.getPlanBudget());
        this.setPlanDesc(plan.getPlanDesc());


    }
}
