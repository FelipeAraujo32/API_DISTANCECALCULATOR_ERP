package com.distancecalculator.distance_calculator_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.service.DistanceModelService;

@Component
public class CustomerCepListenConsumer {

    @Autowired
    DistanceModelService distanceModelService;

    @RabbitListener(queues = "customercep.response.queue")
    public void handleCustomerCepResponse(CepConsumerDto customerCepResponseDto) {
        distanceModelService.updateCustomerCep(customerCepResponseDto);
        
    }
}
