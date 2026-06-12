package com.example.demo.Dto.Users;

import java.time.LocalDate;

import com.example.demo.models.User;

public record UserPutDto(
                String username,
                String name,
                String last_name,
                String second_last_name,
                String password,
                int edad,
                String gmail,
                LocalDate date,
                LocalDate updatePassword,
                boolean isActive,
                Long roleId){

    public void update(User user){
                user.setUsername(this.username);
                user.setName(this.name);
                user.setLast_name(this.last_name);
                user.setSecond_last_name(this.second_last_name);
                user.setPassword(this.password);
                user.setEdad(this.edad);
                user.setGmail(this.gmail);
                user.setIsActive(this.isActive);
            }
}
