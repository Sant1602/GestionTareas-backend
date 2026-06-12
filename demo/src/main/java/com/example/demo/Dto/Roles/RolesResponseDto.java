package com.example.demo.Dto.Roles;

import com.example.demo.models.Role;

public record RolesResponseDto(String code, String name) {
    public RolesResponseDto(Role role) {
        this(
            role.getCode(),
            role.getName());
    }

}
