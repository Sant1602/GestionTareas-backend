package com.example.demo.Dto.Users;
import java.time.LocalDate;



public record UserPostDto(
                String username,
                String name,
                String last_name,
                String second_last_name,
                String password,
                int edad,
                String gmail,
                LocalDate date,
                LocalDate updateDate,
                boolean isActive,
                Long roleId) {
}
