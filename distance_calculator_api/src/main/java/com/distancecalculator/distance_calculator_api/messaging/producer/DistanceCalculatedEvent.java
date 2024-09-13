package com.distancecalculator.distance_calculator_api.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.DistanceCalculatedDto;

@Component
public class DistanceCalculatedEvent {
    
    private final RabbitTemplate rabbitTemplate;

    public DistanceCalculatedEvent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void freightCalculate(DistanceCalculatedDto distanceCalculatedDto){
            rabbitTemplate.convertAndSend(
                "distance.exchange",
                "distance.calculate.event",
                distanceCalculatedDto
            );
    
    }
}
