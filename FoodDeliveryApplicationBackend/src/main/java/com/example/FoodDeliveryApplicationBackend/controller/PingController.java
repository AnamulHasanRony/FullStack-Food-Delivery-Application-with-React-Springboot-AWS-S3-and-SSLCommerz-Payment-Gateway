package com.example.FoodDeliveryApplicationBackend.controller;


import com.example.FoodDeliveryApplicationBackend.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PingController {

    private FoodRepository foodRepository;
    @GetMapping("/ping")
    public String pingToMyAppToMakeItAliveAndMongoDbAtLasAlive() {
        foodRepository.count();
        return "OK";
    }
}
