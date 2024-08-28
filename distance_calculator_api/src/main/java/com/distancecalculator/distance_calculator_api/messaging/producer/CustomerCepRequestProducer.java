package com.distancecalculator.distance_calculator_api.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;

@Component
public class CustomerCepRequestProducer {
    
    private final RabbitTemplate rabbitTemplate;

    public CustomerCepRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void customerCepRequest(CepProducerDto cepProducerDto){
            rabbitTemplate.convertAndSend(
                "distance.exchange",
                "customer.cep.request",
                cepProducerDto
            );
    
    }

}
