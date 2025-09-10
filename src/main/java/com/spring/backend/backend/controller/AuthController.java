package com.spring.backend.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.spring.backend.backend.dto.LoginRequest;
import com.spring.backend.backend.service.UserService;

import java.util.Map;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest.getUser(), loginRequest.getPassword());
        if (token != null) {
            return Map.of("access_token", token);
        } else {
            return Map.of("message", "Usuario o contraseña incorrectos");
        }
    }
}
