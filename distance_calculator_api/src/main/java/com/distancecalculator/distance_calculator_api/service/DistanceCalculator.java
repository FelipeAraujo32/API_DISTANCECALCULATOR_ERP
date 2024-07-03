package com.distancecalculator.distance_calculator_api.service;

public interface DistanceCalculator {
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);
}
