package com.distancecalculator.distance_calculator_api.convert;

import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.DistanceCalculatedDto;
import com.distancecalculator.distance_calculator_api.models.DistanceModel;

@Component
public class DistanceConvert {
    
    public DistanceCalculatedDto toDistanceDTO(DistanceModel distanceModel){
        DistanceCalculatedDto distanceDTO = new DistanceCalculatedDto();
        distanceDTO.setOrderId(distanceModel.getOrderId());
        distanceDTO.setDistance(distanceModel.getDistance());
        distanceDTO.setUnit(distanceModel.getUnit());
        return distanceDTO;
    }
    
    public DistanceModel toDistanceModel(DistanceCalculatedDto distanceDTO){
        DistanceModel distanceModel = new DistanceModel();
        distanceModel.setOrderId(distanceDTO.getOrderId());
        distanceModel.setDistance(distanceDTO.getDistance());
        distanceModel.setUnit(distanceDTO.getUnit());
        return distanceModel;
    }
}
