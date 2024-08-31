package com.distancecalculator.distance_calculator_api.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;

@Component
public class ZipCodeRequestProducer {
    
    private final RabbitTemplate rabbitTemplate;

    public ZipCodeRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void zipCodeRequest(CepProducerDto cepProducerDto){
            rabbitTemplate.convertAndSend(
                "distance.exchange",
                "zipcode.request",
                cepProducerDto
            );
    
    }

}
