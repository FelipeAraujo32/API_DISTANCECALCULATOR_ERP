package com.distancecalculator.distance_calculator_api.dto;

import java.util.UUID;

public class DistanceCalculatedDto {
    
    private UUID orderId;
    private double distance;
    private String unit;
    
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
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
