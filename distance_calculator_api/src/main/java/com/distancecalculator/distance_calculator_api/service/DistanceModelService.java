package com.distancecalculator.distance_calculator_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.convert.DistanceConvert;
import com.distancecalculator.distance_calculator_api.dto.DistanceCalculatedDto;
import com.distancecalculator.distance_calculator_api.dto.cepdto.CepConsumerDto;
import com.distancecalculator.distance_calculator_api.messaging.producer.DistanceCalculatedEvent;
import com.distancecalculator.distance_calculator_api.models.DistanceModel;
import com.distancecalculator.distance_calculator_api.repository.DistanceModelRepository;
import com.distancecalculator.distance_calculator_api.service.distanceprocessing.ProcessDistance;

@Service
public class DistanceModelService {

    private final DistanceModelRepository distanceModelRepository;
    private final ProcessDistance processDistance;
    private final DistanceConvert distanceConvert;
    private final DistanceCalculatedEvent distanceCalculatedEvent;

    public DistanceModelService(DistanceModelRepository distanceModelRepository, ProcessDistance processDistance,
            DistanceConvert distanceConvert, DistanceCalculatedEvent distanceCalculatedEvent) {
        this.distanceModelRepository = distanceModelRepository;
        this.processDistance = processDistance;
        this.distanceConvert = distanceConvert;
        this.distanceCalculatedEvent = distanceCalculatedEvent;
    }

    public void distanceCalculation(UUID orderId) {
        DistanceModel distanceModel = findByOrderId(orderId);
        DistanceModel distanceCalculated = processDistance.calculateAndProcessDistance(distanceModel);
        DistanceModel distanceModeSaved = saveDistanceAndUnit(orderId, distanceCalculated);
        DistanceCalculatedDto distanceModelDto = distanceConvert.toDistanceDTO(distanceModeSaved);
        distanceCalculatedEvent.freightCalculate(distanceModelDto);
    }

    public void updateCustomerCep(CepConsumerDto cepConsumerDto) {
        try {
            DistanceModel distanceModel = findByOrderId(cepConsumerDto.getOrderId());
            distanceModel.setCustomerCep(cepConsumerDto.getCep());
            distanceModelRepository.save(distanceModel);

            if (isReadyForCalculation(cepConsumerDto.getOrderId())) {
                distanceCalculation(cepConsumerDto.getOrderId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating customer zip code.", e);
        }
    }

    public void updateProductCep(CepConsumerDto cepConsumerDto) {
        try {
            DistanceModel distanceModel = findByOrderId(cepConsumerDto.getOrderId());
            distanceModel.setProductCep(cepConsumerDto.getCep());
            distanceModelRepository.save(distanceModel);

            if (isReadyForCalculation(cepConsumerDto.getOrderId())) {
                distanceCalculation(cepConsumerDto.getOrderId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating product zip code.", e);
        }
    }

    private Boolean isReadyForCalculation(UUID orderId) {
        DistanceModel distanceModel = findByOrderId(orderId);
        return areAllCepsReady(distanceModel);
    }

    private Boolean areAllCepsReady(DistanceModel distanceModel) {
        return distanceModel.getCustomerCep() != null && distanceModel.getProductCep() != null;
    }

    private DistanceModel findByOrderId(UUID orderId) {
        return distanceModelRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Not Found OrderId: " + orderId));
    }

    public DistanceModel saveDistanceAndUnit(UUID orderId, DistanceModel distanceModel) {
        DistanceModel distanceModelfind = findByOrderId(orderId);
        distanceModelfind.setDistance(distanceModel.getDistance());
        distanceModelfind.setUnit(distanceModel.getUnit());
        distanceModelRepository.save(distanceModelfind);
        return distanceModelfind;
    }

    public void saveOrderId(UUID orderId) {
        DistanceModel distanceModel = new DistanceModel();
        distanceModel.setOrderId(orderId);
        distanceModelRepository.save(distanceModel);
    }

}
