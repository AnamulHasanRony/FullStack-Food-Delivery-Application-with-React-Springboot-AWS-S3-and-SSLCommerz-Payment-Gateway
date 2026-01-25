package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.OrderRequest;
import com.example.FoodDeliveryApplicationBackend.io.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrderService {

     String createOrderWithPayment(OrderRequest orderRequest);

     void updatePaymentStatus(String orderId, String paid);

     OrderResponse getOrderById(String orderId);
}
