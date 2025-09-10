package com.spring.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUser(String user);
}