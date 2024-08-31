package com.distancecalculator.distance_calculator_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.convert.DistanceConvert;
import com.distancecalculator.distance_calculator_api.dto.DistanceCalculatedDto;
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
        DistanceModel existingDistanceModel = findByOrderId(orderId);
        DistanceModel distanceCalculated = processDistance.calculateAndProcessDistance(existingDistanceModel);
        DistanceModel savedDistanceModel = updateDistanceModel(existingDistanceModel, distanceCalculated);
        sendDistanceCalculatedEvent(savedDistanceModel);
    }

    private DistanceModel updateDistanceModel(DistanceModel existingDistanceModel, DistanceModel distanceCalculated) {
        existingDistanceModel.setDistance(distanceCalculated.getDistance());
        existingDistanceModel.setUnit(distanceCalculated.getUnit());
        return distanceModelRepository.save(existingDistanceModel);
    }

    private DistanceModel findByOrderId(UUID orderId) {
        return distanceModelRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Not Found OrderId: " + orderId));
    }

    private void sendDistanceCalculatedEvent(DistanceModel savedDistanceModel) {
        DistanceCalculatedDto distanceModelDto = distanceConvert.toDistanceDTO(savedDistanceModel);
        distanceCalculatedEvent.freightCalculate(distanceModelDto);
    }

    public void createDistanceModel(UUID orderId) {
        DistanceModel distanceModel = new DistanceModel();
        distanceModel.setOrderId(orderId);
        distanceModelRepository.save(distanceModel);
    }

}
