package com.distancecalculator.distance_calculator_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distancecalculator.distance_calculator_api.service.DistanceCalculator;

@RestController
@RequestMapping("/v1/distance")
public class DistanceController {

    private final DistanceCalculator distanceCalculator;

    public DistanceController(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @GetMapping()
    public double calculateDistance(
        @RequestParam double lat1,
        @RequestParam double lon1,
        @RequestParam double lat2,
        @RequestParam double lon2
    ){
        return distanceCalculator.calculateDistance(lat1, lon1, lat2, lon2);
    }
    
}
