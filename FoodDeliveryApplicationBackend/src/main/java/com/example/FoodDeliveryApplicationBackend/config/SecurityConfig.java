package com.example.FoodDeliveryApplicationBackend.config;

import com.example.FoodDeliveryApplicationBackend.filter.JwtFilter;
import com.example.FoodDeliveryApplicationBackend.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
//,
        httpSecurity.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/payment/**").permitAll()
                        .requestMatchers("/api/register", "/api/login","/api/foods/**").permitAll()
                        .requestMatchers("/api/order/all", "/api/order/update/**").hasRole("ADMIN").anyRequest().authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource){
        return new CorsFilter(corsConfigurationSource());
    }
/// ,

    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config=new CorsConfiguration();//
        config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5177","https://sandbox.sslcommerz.com", "http://localhost:80"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource= new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);


//        CorsConfiguration paymentConfig = new CorsConfiguration();
//        paymentConfig.setAllowedOrigins(List.of("*"));
//        paymentConfig.setAllowedMethods(List.of("POST","GET"));
//        paymentConfig.setAllowedHeaders(List.of("*"));
//        paymentConfig.setAllowCredentials(false);
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/api/payment/**", paymentConfig);
        return urlBasedCorsConfigurationSource;



    }
    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);

    }

}
