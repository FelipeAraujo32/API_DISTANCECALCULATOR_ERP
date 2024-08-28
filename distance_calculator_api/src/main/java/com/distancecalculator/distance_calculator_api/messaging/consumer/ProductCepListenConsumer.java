package com.distancecalculator.distance_calculator_api.messaging.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.service.DistanceModelService;

@Component
public class ProductCepListenConsumer {
    
    @Autowired
    DistanceModelService distanceModelService;

    @RabbitListener(queues = "productcep.response.queue")
    public void handleProductCepResponse(CepConsumerDto productCepResponseDto) {
        distanceModelService.updateProductCep(productCepResponseDto);

    }
}
