package com.example.FoodDeliveryApplicationBackend.controller;


import com.example.FoodDeliveryApplicationBackend.io.FoodRequest;
import com.example.FoodDeliveryApplicationBackend.io.FoodResponse;
import com.example.FoodDeliveryApplicationBackend.service.FoodServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin("*")
public class FoodController {
    @Autowired
    private FoodServiceImplementation foodServiceImplementation;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FoodResponse addFood(@RequestPart("food") String foodString,
                                @RequestPart("file")MultipartFile multipartFile){

        ObjectMapper objectMapper=new ObjectMapper();
        FoodRequest foodRequest=null;

        try{
            foodRequest=objectMapper.readValue(foodString, FoodRequest.class);

        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Json Format");
        }

        FoodResponse foodResponse=foodServiceImplementation.addFood(foodRequest,multipartFile);
        return foodResponse;
    }


    @GetMapping
    public List<FoodResponse> getAllFoods(){
        return foodServiceImplementation.getAllFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable String id){
        return foodServiceImplementation.getFoodById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodById(@PathVariable String id){
        foodServiceImplementation.deleteFoodById(id);
    }
}
