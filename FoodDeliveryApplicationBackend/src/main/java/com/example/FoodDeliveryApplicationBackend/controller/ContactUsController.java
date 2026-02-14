package com.example.FoodDeliveryApplicationBackend.controller;

import com.example.FoodDeliveryApplicationBackend.io.ContactUsRequest;
import com.example.FoodDeliveryApplicationBackend.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contactUs")
@AllArgsConstructor
public class ContactUsController {
    private final ContactUsService contactUsService;

    @PostMapping
    public void submitContactUsForm(@RequestBody ContactUsRequest contactUsRequest){
        contactUsService.saveContactUsFormToDatabase(contactUsRequest);
    }
}
