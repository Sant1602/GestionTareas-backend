package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.PasswordReset;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    PasswordReset findByUser_Id(Long userId);
    PasswordReset findByToken(String token);
    
}
