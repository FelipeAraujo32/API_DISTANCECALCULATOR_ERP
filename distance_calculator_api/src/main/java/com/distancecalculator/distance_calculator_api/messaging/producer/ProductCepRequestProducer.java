package com.distancecalculator.distance_calculator_api.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;

@Component
public class ProductCepRequestProducer {
    
    private final RabbitTemplate rabbitTemplate;

    public ProductCepRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void productCepRequest(CepProducerDto cepProducerDto){
            rabbitTemplate.convertAndSend(
                "distance.exchange",
                "product.cep.request",
                cepProducerDto
            );
    
    }
}
