package com.distancecalculator.distance_calculator_api.service.zipcodeprocessing;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.models.DistanceModel;
import com.distancecalculator.distance_calculator_api.repository.DistanceModelRepository;
import com.distancecalculator.distance_calculator_api.service.DistanceModelService;

@Service
public class ZipCodeReceiptProcessing {

    private final DistanceModelService distanceModelService;
    private final DistanceModelRepository distanceModelRepository;

    public ZipCodeReceiptProcessing(DistanceModelService distanceModelService,
            DistanceModelRepository distanceModelRepository) {
        this.distanceModelService = distanceModelService;
        this.distanceModelRepository = distanceModelRepository;
    }

    public enum CepType {
        CUSTOMER, PRODUCT
    }

    public void updateZipCode(CepConsumerDto cepConsumerDto, CepType cepType) {
        try {
            DistanceModel distanceModel = findByOrderId(cepConsumerDto.getOrderId());
            if (cepType == CepType.CUSTOMER) {
                distanceModel.setCustomerZipCode(cepConsumerDto.getCep());
            } else {
                distanceModel.setProductZipCode(cepConsumerDto.getCep());
            }
            distanceModelRepository.save(distanceModel);
            processDistanceCalculationIfCepsReady(distanceModel);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Zip Code", e);
        }
    }

    public void processDistanceCalculationIfCepsReady(DistanceModel distanceModel) {
        if (areAllCepsReady(distanceModel)) {
            distanceModelService.distanceCalculation(distanceModel.getOrderId());
        }
    }

    private boolean areAllCepsReady(DistanceModel distanceModel) {
        return distanceModel.getCustomerZipCode() != null && distanceModel.getProductZipCode() != null;
    }
    
    private DistanceModel findByOrderId(UUID orderId) {
        return distanceModelRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Not Found OrderId: " + orderId));
    }
}
