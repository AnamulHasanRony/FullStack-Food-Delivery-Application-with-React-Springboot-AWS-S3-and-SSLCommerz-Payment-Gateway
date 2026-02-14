package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.entity.FoodEntity;
import com.example.FoodDeliveryApplicationBackend.entity.OrderEntity;
import com.example.FoodDeliveryApplicationBackend.io.OrderRequest;
import com.example.FoodDeliveryApplicationBackend.io.OrderResponse;
import com.example.FoodDeliveryApplicationBackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @Override
    public String createOrderWithPayment(OrderRequest orderRequest) {
        OrderEntity orderEntity=convertToOrderEntity(orderRequest);

        orderEntity=orderRepository.save(orderEntity);

        return  paymentService.initiatePayment(orderEntity);


    }

    @Override
    public void updatePaymentStatus(String orderId, String paid) {
        OrderEntity orderEntity=orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("order is not found"));
        orderEntity.setPaymentStatus(paid);
        orderRepository.save(orderEntity);

    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        OrderEntity orderEntity=orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("order is not found"));

        return convertToOrderResponse(orderEntity);
    }

    @Override
    public List<OrderResponse> getAllOrderedResponse() {
        String userId= userService.findUserId();
        List<OrderEntity> orderEntityList= orderRepository.findByUserId(userId);
        return orderEntityList.stream().map(orderEntity -> convertToOrderResponse(orderEntity)).collect(Collectors.toList());

    }

    @Override
    public List<OrderResponse> getAllOrderedResponseOfAllUser() {
        List<OrderEntity> orderEntityList= orderRepository.findAll();
        return orderEntityList.stream().map(orderEntity -> convertToOrderResponse(orderEntity)).collect(Collectors.toList());

    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        OrderEntity orderEntity=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException());
        orderEntity.setOrderStatus(status);
        orderRepository.save(orderEntity);

    }

    private OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .customerAddress(orderEntity.getCustomerAddress())
                .customerPhoneNo(orderEntity.getCustomerPhoneNo())
                .customerState(orderEntity.getCustomerState())
                .orderStatus(orderEntity.getOrderStatus())
                .paymentStatus(orderEntity.getPaymentStatus())
                .customerCity(orderEntity.getCustomerCity())
                .customerCountry(orderEntity.getCustomerCountry())
                .customerName(orderEntity.getCustomerName())
                .orderedItem(orderEntity.getOrderedItem())
                .userId(orderEntity.getUserId())
                .totalAmount(orderEntity.getTotalAmount())
                .build();
    }

    private OrderEntity convertToOrderEntity(OrderRequest orderRequest) {
        return OrderEntity.builder()
                .userId(userService.findUserId())
                .customerCountry(orderRequest.getCustomerCountry())
                .customerCity(orderRequest.getCustomerCity())
                .customerEmail(userService.findUserEmail())
                .customerName(orderRequest.getCustomerName())
                .customerState(orderRequest.getCustomerState())
                .orderedItem(orderRequest.getOrderedItem())
                .customerPhoneNo(orderRequest.getCustomerPhoneNo())
                .customerAddress(orderRequest.getCustomerAddress())
                .totalAmount( orderRequest.getTotalAmount())
                .paymentStatus(orderRequest.getPaymentStatus())
                .customerAddress(orderRequest.getCustomerAddress())
                .build();
    }
}
