package com.example.FoodDeliveryApplicationBackend.controller;

import com.example.FoodDeliveryApplicationBackend.io.RegisterRequest;
import com.example.FoodDeliveryApplicationBackend.io.RegisterResponse;
import com.example.FoodDeliveryApplicationBackend.service.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        return userServiceImplementation.registerUser(registerRequest);

    }
}
