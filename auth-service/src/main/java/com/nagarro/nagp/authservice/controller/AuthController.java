package com.nagarro.nagp.authservice.controller;

import com.nagarro.nagp.authservice.model.LoginRequest;
import com.nagarro.nagp.authservice.model.RegistrationRequest;
import com.nagarro.nagp.authservice.model.AuthResponse;
import com.nagarro.nagp.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegistrationRequest authRequest) {
        authService.register(authRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}

