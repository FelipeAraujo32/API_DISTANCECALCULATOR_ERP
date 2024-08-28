package com.distancecalculator.distance_calculator_api.client.cep;

import com.distancecalculator.distance_calculator_api.dto.ApiCepClientDTO;

public interface ApiCepClient {
    ApiCepClientDTO getAddressByCep(String cep);
}
