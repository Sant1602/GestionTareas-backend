package com.example.demo.Dto.Roles;
import com.example.demo.models.Role;

public record RolesPostDto(String code, String name) {
    
    public Role toRole(){
        return new Role(code, name);
    }
    
}
