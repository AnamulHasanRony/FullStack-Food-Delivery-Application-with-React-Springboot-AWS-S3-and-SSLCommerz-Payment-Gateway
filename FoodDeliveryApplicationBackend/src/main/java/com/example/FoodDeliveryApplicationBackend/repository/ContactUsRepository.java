package com.example.FoodDeliveryApplicationBackend.repository;

import com.example.FoodDeliveryApplicationBackend.entity.ContactUsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends MongoRepository<ContactUsEntity, String> {
}
