package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.Login.LoginDto;
import com.example.demo.Dto.Login.LoginEmailDto;
import com.example.demo.Dto.Login.LoginPasswordDto;
import com.example.demo.Dto.Login.LoginResponse;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class LoginController {
    LoginService loginService;
    UserService userService;


    public LoginController(LoginService loginService, UserService userService){
        this.loginService = loginService;
        this.userService = userService;
        
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto) {
        String session = loginService.login(loginDto);
        return ResponseEntity.ok(new LoginResponse(session));
    }

    @GetMapping("/logout")
    public void logoutUser(){ 
    }

     @GetMapping("/me")
    public ResponseEntity<Map<String,String>> getMe(@RequestHeader("Authorization") String Authorization){
        Map<String,String> user = loginService.getMe(Authorization);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody LoginEmailDto loginEmailDto) {
        System.out.println(loginEmailDto.email());
        loginService.tokenEmail(loginEmailDto.email());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/new-password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody LoginPasswordDto request) {
        boolean success = loginService.newPassword(request);
        return ResponseEntity.ok(success);
    }

}
