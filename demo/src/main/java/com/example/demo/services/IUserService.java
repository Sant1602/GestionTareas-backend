package com.example.demo.services;

import java.util.List;

import com.example.demo.Dto.Login.LoginPasswordDto;
import com.example.demo.Dto.Users.UserPostDto;
import com.example.demo.Dto.Users.UserPutDto;
import com.example.demo.Dto.Users.UserResponseDto;


public interface IUserService { 

    List<UserResponseDto> getUsers();
    UserResponseDto createUser(UserPostDto userPostDto);
    UserResponseDto getUserId(long id);
    UserResponseDto putUser(long id, UserPutDto userPutDto);
    void removeUser(long id);
    String updatePassword(String newPassword);
}
