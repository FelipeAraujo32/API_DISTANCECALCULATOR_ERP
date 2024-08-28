package com.distancecalculator.distance_calculator_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distancecalculator.distance_calculator_api.models.DistanceModel;

@Repository
public interface DistanceModelRepository extends JpaRepository<DistanceModel, UUID>{
    Optional<DistanceModel> findByOrderId(UUID orderId);
}
