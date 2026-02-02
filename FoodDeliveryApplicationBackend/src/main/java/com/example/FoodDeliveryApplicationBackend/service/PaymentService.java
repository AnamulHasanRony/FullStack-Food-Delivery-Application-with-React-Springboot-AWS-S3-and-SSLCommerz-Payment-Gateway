package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.entity.OrderEntity;

public interface PaymentService {
    String initiatePayment(OrderEntity orderEntity);

    boolean verifyPayment(String tranId, String valId);
}
