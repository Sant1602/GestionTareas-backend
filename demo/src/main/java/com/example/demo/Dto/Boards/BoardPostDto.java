package com.example.demo.Dto.Boards;

public record BoardPostDto(String code, String name, String description, Boolean isActive,
        Long ProjectId) {
}
