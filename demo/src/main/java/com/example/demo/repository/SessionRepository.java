package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Sesion;


public interface SessionRepository extends JpaRepository<Sesion, Long> {
    Sesion findByUserId(Long userId);
    Sesion findByTokenRefresh(String token);

    
}
