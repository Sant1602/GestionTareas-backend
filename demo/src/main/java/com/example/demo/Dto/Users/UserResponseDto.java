package com.example.demo.Dto.Users;

import java.time.LocalDate;

import com.example.demo.models.User;

public record UserResponseDto(
        Long id,
        String username,
        String name,
        String last_name,
        String second_last_name,
        String password,
        int edad,
        LocalDate date,
        LocalDate updateDate,
        Long roleId) {

    public UserResponseDto(User user) {
        this(
            user.getId(), 
            user.getUsername(),
            user.getName(), 
            user.getLast_name(),
            user.getSecond_last_name(),
            user.getPassword(),
            user.getEdad(),
            user.getDate(),
            user.getUpdateDate(),
            user.getRole().getId());
    }
}
