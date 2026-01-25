package com.example.FoodDeliveryApplicationBackend.controller;

import com.example.FoodDeliveryApplicationBackend.entity.CartEntity;
import com.example.FoodDeliveryApplicationBackend.io.CartResponse;
import com.example.FoodDeliveryApplicationBackend.io.FoodResponse;
import com.example.FoodDeliveryApplicationBackend.io.OrderItem;
import com.example.FoodDeliveryApplicationBackend.io.OrderRequest;
import com.example.FoodDeliveryApplicationBackend.service.CartService;
import com.example.FoodDeliveryApplicationBackend.service.FoodService;
import com.example.FoodDeliveryApplicationBackend.service.OrderService;
import com.example.FoodDeliveryApplicationBackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final FoodService foodService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createOrderWithPayment(@RequestBody OrderRequest orderRequest){

        CartResponse cartResponse=cartService.getCart();
        if(cartResponse==null || cartResponse.getItems().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("error","Cart is empty"));
        }

         orderRequest = createOrderRequestWithPartialOrderRequestObject(orderRequest,cartResponse);

         String paymentUrl= orderService.createOrderWithPayment(orderRequest);
        return ResponseEntity.ok(Map.of("paymentUrl", paymentUrl));


    }

    private OrderRequest createOrderRequestWithPartialOrderRequestObject(OrderRequest orderRequest, CartResponse cartResponse) {


        List<OrderItem> orderItems = cartResponse.getItems().entrySet().stream().map(entry -> {
            String foodId = entry.getKey();
            int quantity = entry.getValue();
            FoodResponse foodResponse = foodService.getFoodById(foodId);

            double price=foodResponse.getPrice();
            String description=foodResponse.getDescription();
            String imageUrl=foodResponse.getImageUrl();
            String name=foodResponse.getName();
            String category=foodResponse.getCategory();
            return new OrderItem(foodId, quantity, price, description, imageUrl, name, category);
        }).toList();

        orderRequest.setOrderedItem(orderItems);
        orderRequest.setPaymentStatus("pending");
        double subTotal = orderItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        double totalAmount=subTotal+subTotal*0.1+50.0;
        orderRequest.setTotalAmount(totalAmount);
        orderRequest.setCustomerEmail(userService.findUserEmail());
        orderRequest.setUserId(userService.findUserId());
        return orderRequest;
    }


}
