package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.OrderRequest;
import com.example.FoodDeliveryApplicationBackend.io.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {

     String createOrderWithPayment(OrderRequest orderRequest);

     void updatePaymentStatus(String orderId, String paid);

     OrderResponse getOrderById(String orderId);

     List<OrderResponse> getAllOrderedResponse();

     List<OrderResponse> getAllOrderedResponseOfAllUser();

     void updateOrderStatus(String orderId, String status);
}
