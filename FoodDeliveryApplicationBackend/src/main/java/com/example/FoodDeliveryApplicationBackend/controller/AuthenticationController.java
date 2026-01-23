package com.example.FoodDeliveryApplicationBackend.controller;

import com.example.FoodDeliveryApplicationBackend.Utils.JwtUtil;
import com.example.FoodDeliveryApplicationBackend.io.AuthenticationRequest;
import com.example.FoodDeliveryApplicationBackend.io.AuthenticationResponse;
import com.example.FoodDeliveryApplicationBackend.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(authenticationRequest.getEmail(), jwtToken);
    }

}
