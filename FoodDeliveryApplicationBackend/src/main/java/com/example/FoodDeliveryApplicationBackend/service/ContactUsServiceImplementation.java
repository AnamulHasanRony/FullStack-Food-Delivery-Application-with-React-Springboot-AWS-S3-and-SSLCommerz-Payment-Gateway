package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.entity.ContactUsEntity;
import com.example.FoodDeliveryApplicationBackend.io.ContactUsRequest;
import com.example.FoodDeliveryApplicationBackend.repository.ContactUsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactUsServiceImplementation implements ContactUsService{

    private final ContactUsRepository contactUsRepository;
    @Override
    public void saveContactUsFormToDatabase(ContactUsRequest contactUsRequest) {
        ContactUsEntity contactUsEntity=convertContactUsRequestToEntity(contactUsRequest);
        contactUsRepository.save(contactUsEntity);

    }


    private ContactUsEntity convertContactUsRequestToEntity(ContactUsRequest contactUsRequest){
        return ContactUsEntity.builder()
                .email(contactUsRequest.getEmail())
                .name(contactUsRequest.getName())
                .message(contactUsRequest.getMessage())
                .build();
    }
}
