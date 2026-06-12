package com.example.demo.services;

import java.util.List;


import com.example.demo.Dto.Roles.RolesPostDto;
import com.example.demo.Dto.Roles.RolesPutDto;
import com.example.demo.Dto.Roles.RolesResponseDto;


public interface IRoleService {

    List<RolesResponseDto> getRoles();
    RolesResponseDto createRoles(RolesPostDto rolesPostDto);
    RolesResponseDto putRoles(Long id, RolesPutDto rolesPutDto);
    boolean removeRole(Long id);
    
}
