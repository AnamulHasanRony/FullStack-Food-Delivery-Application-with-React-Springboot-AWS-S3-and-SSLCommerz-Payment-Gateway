package com.example.FoodDeliveryApplicationBackend.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsRequest {
    private String name;
    private String email;
    private String message;
}
