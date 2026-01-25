package com.example.FoodDeliveryApplicationBackend.io;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    private String id;
    private String userId;
    private String paymentStatus;
    private String customerName;
    private String customerPhoneNo;
    private String customerAddress;
    private String customerCountry;
    private String customerCity;
    private String customerState;
    private  String orderStatus;


}
