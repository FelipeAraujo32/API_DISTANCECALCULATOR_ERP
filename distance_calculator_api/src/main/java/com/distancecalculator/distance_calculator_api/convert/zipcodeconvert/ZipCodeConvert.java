package com.distancecalculator.distance_calculator_api.convert.zipcodeconvert;

import com.distancecalculator.distance_calculator_api.dto.OrderModelDto;
import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;

public class ZipCodeConvert {
    
    public CepProducerDto toZipCodeProducer(OrderModelDto orderModelDto){
        CepProducerDto cepProducerDto = new CepProducerDto();
        cepProducerDto.setOrderId(orderModelDto.getOrderId());
        cepProducerDto.setProductId(orderModelDto.getProductId());
        cepProducerDto.setCustomerId(orderModelDto.getCustomerId());
        return cepProducerDto;
    }
}
