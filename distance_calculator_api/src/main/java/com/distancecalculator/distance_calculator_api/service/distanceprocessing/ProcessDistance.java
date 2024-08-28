package com.distancecalculator.distance_calculator_api.service.distanceprocessing;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.dto.ApiCepClientDTO;
import com.distancecalculator.distance_calculator_api.models.DistanceModel;

@Service
public class ProcessDistance {

    private final SearchZipCode searchZipCode;
    private final HaversineDistanceCalculator haversineDistanceCalculator;

    public ProcessDistance(SearchZipCode searchZipCode, HaversineDistanceCalculator haversineDistanceCalculator) {
        this.searchZipCode = searchZipCode;
        this.haversineDistanceCalculator = haversineDistanceCalculator;
    }

    public DistanceModel calculateAndProcessDistance(DistanceModel distanceModel){
        ApiCepClientDTO cepProducut = searchZipCode.getCepAPI(distanceModel.getProductCep());
        ApiCepClientDTO cepCustomer = searchZipCode.getCepAPI(distanceModel.getCustomerCep());
        return haversineDistanceCalculator.calculateDistance(cepProducut, cepCustomer);

        
    }
}


