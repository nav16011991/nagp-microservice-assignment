package com.nagarro.nagp.authservice.service;


import com.google.gson.Gson;
import com.nagarro.nagp.authservice.model.AccountInfoResponse;
import com.nagarro.nagp.authservice.model.AuthResponse;
import com.nagarro.nagp.authservice.model.LoginRequest;
import com.nagarro.nagp.authservice.model.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwt;
    private final DiscoveryClient discoveryClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public AuthService(RestTemplate restTemplate,
                       final JwtUtil jwt, DiscoveryClient discoveryClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.jwt = jwt;
        this.discoveryClient = discoveryClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public void register(RegistrationRequest authRequest) {

        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        try {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

            circuitBreaker.run(() -> {
                List<ServiceInstance> instances = discoveryClient.getInstances("ACCOUNT-SERVICE");
                String accountServiceUrl = instances.get(0).getUri()+"/account/";
                HttpEntity<RegistrationRequest> registrationRequestHttpEntity = new HttpEntity<>(authRequest);
                ResponseEntity<String> response = restTemplate.exchange(accountServiceUrl,
                        HttpMethod.POST,
                        registrationRequestHttpEntity,
                        String.class);
                return new Gson().fromJson(response.getBody(), AccountInfoResponse.class);
            }, throwable -> {
                return null;
            });

        } catch (RestClientException ex) {
            throw ex;
        }

    }

    public AuthResponse login(LoginRequest loginRequest) throws Exception {
        AccountInfoResponse accountInfoResponse = null;
        try {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

            accountInfoResponse = circuitBreaker.run(() -> {
                List<ServiceInstance> instances = discoveryClient.getInstances("ACCOUNT-SERVICE");
                String accountServiceUrl = instances.get(0).getUri()+"/account/user/"+ loginRequest.getUsername();
                ResponseEntity<String> response = restTemplate.exchange(accountServiceUrl,
                        HttpMethod.GET,
                        null,
                        String.class);
                return new Gson().fromJson(response.getBody(), AccountInfoResponse.class);
            }, throwable -> {
                return null;
            });

        } catch (RestClientException ex) {
            throw ex;
        }
        if(ObjectUtils.isEmpty(accountInfoResponse)) {
            throw  new Exception("User not found");
        }
        if(!BCrypt.checkpw(loginRequest.getPassword(), accountInfoResponse.getPassword())) {
            throw new Exception("Invalid credential");
        }
        String accessToken = jwt.generate(accountInfoResponse, "ACCESS");
        String refreshToken = jwt.generate(accountInfoResponse, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
}
