package com.spring.backend.backend.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "El usuario es obligatorio")
    @JsonProperty("username")
    private String user;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Getters y setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}