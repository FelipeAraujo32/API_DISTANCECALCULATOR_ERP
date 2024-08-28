package com.distancecalculator.distance_calculator_api.dto.cepdto;

import java.util.UUID;

public class CepConsumerDto {
    private UUID orderId;
    private String cep;
    
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

}
