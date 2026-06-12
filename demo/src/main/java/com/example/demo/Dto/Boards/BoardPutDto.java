package com.example.demo.Dto.Boards;

import com.example.demo.models.Board;

public record BoardPutDto (Long Id, String code, String name, String description, boolean isActive,
        Long ProjectId) {

            public void update(Board board){
                board.setCode(this.code);
                board.setName(this.name);
                board.setDescription(this.description);
                board.setActive(this.isActive);
            }
    
}
