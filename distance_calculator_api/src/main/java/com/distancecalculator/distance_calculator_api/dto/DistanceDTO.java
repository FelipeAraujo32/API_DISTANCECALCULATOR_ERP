package com.distancecalculator.distance_calculator_api.dto;

public class DistanceDTO {
    
    private double distance;
    private String unit;
    
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
