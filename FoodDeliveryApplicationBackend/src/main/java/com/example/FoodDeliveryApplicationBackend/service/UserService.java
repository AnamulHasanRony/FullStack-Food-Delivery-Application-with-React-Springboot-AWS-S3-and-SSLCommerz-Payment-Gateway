package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.RegisterRequest;
import com.example.FoodDeliveryApplicationBackend.io.RegisterResponse;

public interface UserService {
    RegisterResponse registerUser(RegisterRequest registerRequest);
    String findUserId();
    String findUserEmail();


}
