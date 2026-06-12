package com.example.demo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Dto.Login.LoginPasswordDto;
import com.example.demo.Dto.Users.UserPostDto;
import com.example.demo.Dto.Users.UserPutDto;
import com.example.demo.Dto.Users.UserResponseDto;
import com.example.demo.mapper.Mappers;
import com.example.demo.models.Role;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    Argon2 argon2 = Argon2Factory.create();

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return Mappers.toDto(users, UserResponseDto::new);
    }

    @Override
    public UserResponseDto createUser(UserPostDto userPostDto) {
        String PasswordHash;

        if (userPostDto.edad() < 18 || !verifyPassword(userPostDto.password())
                || userRepository.existsByGmail(userPostDto.gmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear user");
        }

        Role role = roleRepository.findById(userPostDto.roleId())
                .orElseThrow(() -> new RuntimeException("El rol no existe"));
        PasswordHash = passwordHash(userPostDto.password().toCharArray());

        User user = new User(userPostDto.username(),
                userPostDto.name(),
                userPostDto.last_name(),
                userPostDto.second_last_name(),
                PasswordHash,
                userPostDto.edad(),
                userPostDto.gmail(),
                userPostDto.date(),
                null,
                null,
                userPostDto.isActive(),
                role);

        user.setDate(userPostDto.date());
        user = userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserId(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto putUser(long id, UserPutDto userPutDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        if (!user.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no está activo");
        }
        // Validar contraseña antes de modificar
        if (!user.getPassword().equals(userPutDto.password())) {
            if (!verifyPassword(userPutDto.password())) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La contraseña no cumple criterios");
            }
            user.setUpdatePassword(userPutDto.updatePassword());
        }
        Role role = roleRepository.findById(userPutDto.roleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        user.setUpdateDate(userPutDto.date());
        user.setRole(role);
        userPutDto.update(user);
        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    public void removeUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontro el usuario"));
        if ("Administrador".equals(user.getRole().getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes eliminar a este usuario");
        }
        userRepository.delete(user);
    }

    @Override
    public String updatePassword(String newPassword) {
        if (!verifyPassword(newPassword)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La contraseña no cumple criterios");
        }
        return passwordHash(newPassword.toCharArray());
    }

    // Reglas de negocio
    // Hash de contraseñas
    private String passwordHash(char[] password) {
        try {
            return argon2.hash(10, 65536, 1, password);
        } finally {
            argon2.wipeArray(password);
        }
    }

    // Contraseña mayor a 8 digitos
    private boolean verifyPassword(String password) {
        boolean hasUpper = false;
        boolean hasDigit = false;

        if (password.length() <= 7) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return hasUpper && hasDigit;
    }

}
