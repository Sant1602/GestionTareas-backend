package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.Roles.RolesPostDto;
import com.example.demo.Dto.Roles.RolesPutDto;
import com.example.demo.Dto.Roles.RolesResponseDto;
import com.example.demo.services.IRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/roles")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

@GetMapping("/")
public ResponseEntity<List<RolesResponseDto>> getRoles() {
    List<RolesResponseDto> roles = roleService.getRoles();
    return ResponseEntity.ok(roles);
}

@PostMapping("/")
public ResponseEntity<RolesResponseDto> postRole(@RequestBody RolesPostDto rolesPostDto){
    RolesResponseDto role = roleService.createRoles(rolesPostDto);
    return ResponseEntity.ok(role);
}

@PutMapping("/{id}")
public ResponseEntity<RolesResponseDto> putRoles(@PathVariable long id, @RequestBody RolesPutDto rolesPutDto){
    RolesResponseDto role = roleService.putRoles(id, rolesPutDto);{
    return ResponseEntity.ok(role);
}
}

@DeleteMapping("/{id}")
public ResponseEntity<Boolean> deleteRole(@PathVariable long id){
    boolean query = roleService.removeRole(id);
    return ResponseEntity.ok(query);
}
}
