package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.FoodRequest;
import com.example.FoodDeliveryApplicationBackend.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
    String uploadFile(MultipartFile multipartFile);
    FoodResponse addFood(FoodRequest foodRequest, MultipartFile multipartFile);
    List<FoodResponse> getAllFoods();
    FoodResponse getFoodById(String id);
    void deleteFoodById(String id);

}
