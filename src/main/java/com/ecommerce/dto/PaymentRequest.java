package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PaymentRequest {

    @NotBlank(message = "Order ID is required")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}