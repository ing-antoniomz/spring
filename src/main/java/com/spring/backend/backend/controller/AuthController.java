package com.spring.backend.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.spring.backend.backend.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        String token = userService.login(username, password);
        if (token != null) {
            return Map.of("access_token", token);
        } else {
            return Map.of("message", "Usuario o contraseña incorrectos");
        }
    }
}
