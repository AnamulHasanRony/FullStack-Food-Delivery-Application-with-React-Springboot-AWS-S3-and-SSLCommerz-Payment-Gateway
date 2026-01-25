package com.example.FoodDeliveryApplicationBackend.controller;

import com.example.FoodDeliveryApplicationBackend.io.CartRequest;
import com.example.FoodDeliveryApplicationBackend.io.CartResponse;
import com.example.FoodDeliveryApplicationBackend.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping
    public CartResponse addToCart(@RequestBody CartRequest cartRequest){
        String foodId=cartRequest.getFoodId();
        if(foodId==null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "food id not found exception");
        }
        return cartService.addToCart(foodId);

    }

    @GetMapping
    public CartResponse getCart(){
        return cartService.getCart();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(){
        cartService.clearCart();
    }


    @PostMapping("/remove")
    public CartResponse removeFromCart(@RequestBody CartRequest cartRequest){
        String foodId=cartRequest.getFoodId();
        if(foodId==null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "food id not found exception");
        }
        return cartService.removeFromCart(foodId);

    }

    @PostMapping("/delete")
    public CartResponse deleteFromCart(@RequestBody CartRequest cartRequest){
        String foodId=cartRequest.getFoodId();

        if(foodId==null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "food id not found exception");
        }
        return cartService.deleteFromCart(foodId);

    }
}
