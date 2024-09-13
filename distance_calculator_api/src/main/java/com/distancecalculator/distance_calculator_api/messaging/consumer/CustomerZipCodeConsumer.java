package com.distancecalculator.distance_calculator_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.service.zipcodeprocessing.ZipCodeReceiptProcessing;
import com.distancecalculator.distance_calculator_api.service.zipcodeprocessing.ZipCodeReceiptProcessing.CepType;

@Component
public class CustomerZipCodeConsumer {

    private final ZipCodeReceiptProcessing zipCodeProcessing;

    public CustomerZipCodeConsumer(ZipCodeReceiptProcessing zipCodeProcessing) {
        this.zipCodeProcessing = zipCodeProcessing;
    }

    @RabbitListener(queues = "customerzipcode.response.queue")
    public void handleCustomerCepResponse(CepConsumerDto customerCepResponseDto) {
        zipCodeProcessing.updateZipCode(customerCepResponseDto, CepType.PRODUCT);
        
    }
}
