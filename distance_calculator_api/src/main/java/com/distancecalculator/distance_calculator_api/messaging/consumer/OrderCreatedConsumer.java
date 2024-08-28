package com.distancecalculator.distance_calculator_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.convert.cepconvert.CepConvert;
import com.distancecalculator.distance_calculator_api.dto.OrderModelDto;
import com.distancecalculator.distance_calculator_api.dto.cepdto.CepProducerDto;
import com.distancecalculator.distance_calculator_api.messaging.producer.CustomerCepRequestProducer;
import com.distancecalculator.distance_calculator_api.messaging.producer.ProductCepRequestProducer;
import com.distancecalculator.distance_calculator_api.service.DistanceModelService;

@Component
public class OrderCreatedConsumer {

    private final CustomerCepRequestProducer customerCepRequestProducer;
    private final ProductCepRequestProducer productCepRequestProducer;
    private final DistanceModelService distanceModelService;

    public OrderCreatedConsumer(CustomerCepRequestProducer customerCepRequestProducer,
            ProductCepRequestProducer productCepRequestProducer, DistanceModelService distanceModelService) {
        this.customerCepRequestProducer = customerCepRequestProducer;
        this.productCepRequestProducer = productCepRequestProducer;
        this.distanceModelService = distanceModelService;
    }

    @RabbitListener(queues = "order.created.queue")
    public void handleOrderCreated(OrderModelDto orderModelDto){
        distanceModelService.saveOrderId(orderModelDto.getOrderId());
        CepProducerDto cepProducerDto = new CepConvert().toCepProducerDto(orderModelDto);
        customerCepRequestProducer.customerCepRequest(cepProducerDto);
        productCepRequestProducer.productCepRequest(cepProducerDto);
    }

}
