package com.distancecalculator.distance_calculator_api.client.cep;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.distancecalculator.distance_calculator_api.dto.ApiCepClientDTO;


@Component
public class ApiCepClientImpl implements ApiCepClient{

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://cep.awesomeapi.com.br/json";

    public ApiCepClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ApiCepClientDTO getAddressByCep(String cep) {
        String url = String.format("%s/%s", BASE_URL, cep);
        return restTemplate.getForObject(url, ApiCepClientDTO.class);
    }
}
