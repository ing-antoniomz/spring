package com.spring.backend.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.backend.backend.model.User;
import com.spring.backend.backend.repository.UserRepository;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String SECRET_KEY = "estaEsUnaClaveSuperSeguraDeAlMenos32Bytes!";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUser(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return generateToken(userOpt.get());
        }
        return null;
    }

    private String generateToken(User user) {
        long expirationTime = 1000 * 60 * 60; // 1 hora

        // Convierte tu SECRET_KEY a un Key de forma segura
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(user.getUser())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key) // ahora sin usar SignatureAlgorithm
                .compact();
    }
}