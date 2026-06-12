package com.example.demo.Dto.Sessions;

import java.time.LocalDateTime;

import com.example.demo.models.Sesion;
import com.example.demo.models.User;

import jakarta.transaction.Transactional;

@Transactional
public record SessionResponseDto(String token, LocalDateTime DateExpire, User user){
    public SessionResponseDto(Sesion sesion){
        this(sesion.getTokenRefresh(), 
        sesion.getDateExpire(),
        sesion.getUser());
    }
}
