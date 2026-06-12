package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.Roles.RolesPostDto;
import com.example.demo.Dto.Roles.RolesPutDto;
import com.example.demo.Dto.Roles.RolesResponseDto;
import com.example.demo.mapper.Mappers;
import com.example.demo.models.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RolesResponseDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return Mappers.toDto(roles, RolesResponseDto::new);
            }

    @Override
    public RolesResponseDto createRoles(RolesPostDto rolesPostDto) {
        Role role = rolesPostDto.toRole();
        Role savedRole = roleRepository.save(role);
        return new RolesResponseDto(savedRole);
    }

    @Override
    public RolesResponseDto putRoles(Long id, RolesPutDto rolesPutDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        rolesPutDto.update(role);
        Role updatedRole = roleRepository.save(role);
        return new RolesResponseDto(updatedRole);
    }

    @Override
    public boolean removeRole(Long id){
        roleRepository.deleteById(id);
        return true;

    }
}
