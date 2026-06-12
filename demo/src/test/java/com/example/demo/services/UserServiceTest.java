package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Dto.Users.UserPostDto;
import com.example.demo.Dto.Users.UserResponseDto;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUsers() {

        User user = new User("Pepe15", "Pepe", "Lopez", "Alzate",
                "Pepe1520", 20, "pepe@gmail.com", LocalDate.now(), null, null, false, null);

        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> listUsers = userRepository.findAll();

        assertNotNull(listUsers);
        assertEquals(listUsers.size(), 1);
        assertTrue("Pepe".equals(users.get(0).getName()));
    }

    @Test
    void testCreateUser() {
        UserPostDto user = new UserPostDto("Santi99", "Santiago", "Alvarez", "Gomez",
                "Password1", 22, "santi@gmail.com", LocalDate.now(), null, true, 1L);
        when(roleRepository.findById(user.roleId())).thenAnswer(u -> {Role role = new Role("ABC-123", "PruebasAdmin");
            role.setId(1L);
            return Optional.of(role);
        }
    );
        when(userRepository.existsByGmail(user.gmail())).thenReturn(false);
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User u = invocation.getArgument(0);
                    u.setId(1L);
                    return u;
                });
        UserResponseDto res = userService.createUser(user);
        assertNotNull(res);
        assertEquals(user.name(), res.name());

    }

}
