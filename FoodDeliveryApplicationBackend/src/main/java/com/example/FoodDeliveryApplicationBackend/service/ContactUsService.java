package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.io.ContactUsRequest;

public interface ContactUsService {

    public void saveContactUsFormToDatabase(ContactUsRequest contactUsRequest);
}
