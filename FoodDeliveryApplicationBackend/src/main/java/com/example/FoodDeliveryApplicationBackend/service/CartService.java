package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.CartResponse;

public interface CartService {
   CartResponse addToCart(String foodId);
   CartResponse getCart();
   void clearCart();
   CartResponse removeFromCart(String foodId);

   CartResponse deleteFromCart(String foodId);
}
