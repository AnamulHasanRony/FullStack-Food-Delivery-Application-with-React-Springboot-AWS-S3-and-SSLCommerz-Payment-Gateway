package com.example.FoodDeliveryApplicationBackend.service;

import com.example.FoodDeliveryApplicationBackend.entity.UserEntity;
import com.example.FoodDeliveryApplicationBackend.io.RegisterRequest;
import com.example.FoodDeliveryApplicationBackend.io.RegisterResponse;
import com.example.FoodDeliveryApplicationBackend.io.UserResponse;
import com.example.FoodDeliveryApplicationBackend.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements  UserService{

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationFacade authenticationFacade;

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        UserEntity newUserEntity=convertToUserEntity((registerRequest));
        if(userRepository.existsByEmail(newUserEntity.getEmail())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid input data"
            );
        }
        newUserEntity.setRole("ROLE_USER");
        newUserEntity=userRepository.save(newUserEntity);

        return convertToRegisterResponse(newUserEntity);

    }

    @Override
    public String findUserId() {

        String loggedInEmail= authenticationFacade.getAuthentication().getName();
        UserEntity userEntity=userRepository.findByEmail(loggedInEmail).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return userEntity.getId();

    }

    @Override
    public String findUserEmail() {

        String loggedInEmail= authenticationFacade.getAuthentication().getName();
        return loggedInEmail;

    }

    private UserEntity convertToUserEntity(RegisterRequest registerRequest){
        return UserEntity.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .build();

    }

    private RegisterResponse convertToRegisterResponse(UserEntity userEntity){
        return RegisterResponse.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .id(userEntity.getId())
                .build();

    }

}
