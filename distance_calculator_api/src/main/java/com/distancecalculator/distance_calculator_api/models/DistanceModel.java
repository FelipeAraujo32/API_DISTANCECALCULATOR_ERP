package com.distancecalculator.distance_calculator_api.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "distanceCalculated")
@Table(name = "distance_erp")
public class DistanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID distanceId;

    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "distance")
    private double distance;

    @Column(name = "unit")
    private String unit;

    @Column(name = "customer_cep")
    private String customerCep;

    @Column(name = "product_cep")
    private String productCep;

    public DistanceModel() {
    }

    public DistanceModel(UUID orderId, double distance, String unit, String customerCep, String productCep) {
        this.orderId = orderId;
        this.distance = distance;
        this.unit = unit;
        this.customerCep = customerCep;
        this.productCep = productCep;
    }

    public UUID getDistanceId() {
        return distanceId;
    }

    public void setDistanceId(UUID distanceId) {
        this.distanceId = distanceId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCustomerCep() {
        return customerCep;
    }

    public void setCustomerCep(String customerCep) {
        this.customerCep = customerCep;
    }

    public String getProductCep() {
        return productCep;
    }

    public void setProductCep(String productCep) {
        this.productCep = productCep;
    }

}
