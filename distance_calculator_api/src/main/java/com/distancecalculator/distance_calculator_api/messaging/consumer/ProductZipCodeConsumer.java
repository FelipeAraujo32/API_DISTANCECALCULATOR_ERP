package com.distancecalculator.distance_calculator_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.service.zipcodeprocessing.ZipCodeReceiptProcessing;
import com.distancecalculator.distance_calculator_api.service.zipcodeprocessing.ZipCodeReceiptProcessing.CepType;

@Component
public class ProductZipCodeConsumer {

    private final ZipCodeReceiptProcessing zipCodeProcessing;

    public ProductZipCodeConsumer(ZipCodeReceiptProcessing zipCodeProcessing) {
        this.zipCodeProcessing = zipCodeProcessing;
    }

    @RabbitListener(queues = "productcep.response.queue")
    public void handleProductCepResponse(CepConsumerDto productCepResponseDto) {
        zipCodeProcessing.updateZipCode(productCepResponseDto, CepType.CUSTOMER);

    }
}
