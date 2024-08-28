package com.distancecalculator.distance_calculator_api.dto.cepdto;

import java.util.UUID;

public class CepProducerDto {
    private UUID orderId;
    private UUID productId;
    private UUID customerId;
    
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    public UUID getProductId() {
        return productId;
    }
    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    public UUID getCustomerId() {
        return customerId;
    }
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

}
