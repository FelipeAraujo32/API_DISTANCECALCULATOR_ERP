package com.distancecalculator.distance_calculator_api.service.distanceprocessing;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.client.cep.ApiCepClient;
import com.distancecalculator.distance_calculator_api.dto.ApiCepClientDTO;

@Service
public class SearchZipCode {

    private final ApiCepClient apiCepClient;

    public SearchZipCode(ApiCepClient apiCepClient) {
        this.apiCepClient = apiCepClient;
    }

    public ApiCepClientDTO getCepAPI(String cep) {
        return apiCepClient.getAddressByCep(cep);
    }

}