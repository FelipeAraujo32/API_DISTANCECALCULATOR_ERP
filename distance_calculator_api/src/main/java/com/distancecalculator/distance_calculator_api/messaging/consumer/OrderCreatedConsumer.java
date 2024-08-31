package com.distancecalculator.distance_calculator_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.convert.zipcodeconvert.ZipCodeConvert;
import com.distancecalculator.distance_calculator_api.dto.OrderModelDto;
import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;
import com.distancecalculator.distance_calculator_api.messaging.producer.ZipCodeRequestProducer;
import com.distancecalculator.distance_calculator_api.service.DistanceModelService;

@Component
public class OrderCreatedConsumer {

    private final ZipCodeRequestProducer zipCodeRequestProducer;
    private final DistanceModelService distanceModelService;

    public OrderCreatedConsumer(ZipCodeRequestProducer zipCodeRequestProducer,
            DistanceModelService distanceModelService) {
        this.zipCodeRequestProducer = zipCodeRequestProducer;
        this.distanceModelService = distanceModelService;
    }

    @RabbitListener(queues = "order.created.queue")
    public void handleOrderCreated(OrderModelDto orderModelDto){
        distanceModelService.createDistanceModel(orderModelDto.getOrderId());
        CepProducerDto cepProducerDto = new ZipCodeConvert().toZipCodeProducer(orderModelDto);
        zipCodeRequestProducer.zipCodeRequest(cepProducerDto);
    }

}
