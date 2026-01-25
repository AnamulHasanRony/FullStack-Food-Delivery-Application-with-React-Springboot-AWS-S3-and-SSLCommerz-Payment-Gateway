package com.example.FoodDeliveryApplicationBackend.entity;

import com.example.FoodDeliveryApplicationBackend.io.OrderItem;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder
public class OrderEntity {
     private String id;
     private String userId;
     private String customerEmail;
     private List<OrderItem> orderedItem;
     private double totalAmount;
     private String paymentStatus;
     private String customerName;
     private String customerPhoneNo;
     private String customerAddress;
     private String customerCountry;
     private String customerCity;
     private String customerState;
     private  String orderStatus;

}
