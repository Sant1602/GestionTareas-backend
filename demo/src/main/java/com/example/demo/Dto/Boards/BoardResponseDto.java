package com.example.demo.Dto.Boards;

import com.example.demo.models.Board;

public record BoardResponseDto(Long Id, String code, String name, String description, Boolean isActive,
        Long ProjectId) {
    public BoardResponseDto(Board board) {
        this(board.getId(), board.getCode(), board.getName(), board.getDescription(), board.isActive(),
                board.getProject().getId());
    }
}
