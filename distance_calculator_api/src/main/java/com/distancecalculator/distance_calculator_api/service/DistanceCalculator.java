package com.distancecalculator.distance_calculator_api.service;

import com.distancecalculator.distance_calculator_api.dto.DistanceDTO;

public interface DistanceCalculator {
    DistanceDTO calculateDistance(double lat1, double lon1, double lat2, double lon2);
}
