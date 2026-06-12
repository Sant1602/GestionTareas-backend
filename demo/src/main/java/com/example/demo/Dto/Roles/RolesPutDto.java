package com.example.demo.Dto.Roles;

import com.example.demo.models.Role;

public record RolesPutDto(String code, String name){
    public void update(Role role){
        role.setCode(this.code);
        role.setName(this.name);
    }    
}
