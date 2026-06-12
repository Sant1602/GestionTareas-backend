package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Dto.Users.UserPostDto;
import com.example.demo.Dto.Users.UserPutDto;
import com.example.demo.Dto.Users.UserResponseDto;
import com.example.demo.auth.RequireAuth;
import com.example.demo.services.IUserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // GET /users
    @GetMapping
    @RequireAuth(role = "Administrador")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/")
    @RequireAuth(role = "Administrador")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserId(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    //@RequireAuth(role = "Administrador")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserPostDto userDto) {
            UserResponseDto createdUser = userService.createUser(userDto);
            return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    @RequireAuth(role = "Administrador")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable long id, @RequestBody UserPutDto userDto){
        UserResponseDto user = userService.putUser(id, userDto);
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/{id}")
    //@RequireAuth(role = "Administrador")
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    } 
}
