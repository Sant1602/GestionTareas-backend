package com.example.demo.services;

import com.example.demo.Dto.Login.LoginDto;
import com.example.demo.Dto.Login.LoginPasswordDto;
import com.example.demo.models.PasswordReset;
import com.example.demo.models.Sesion;
import com.example.demo.models.User;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.repository.PasswordResetRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final UserService userService;
    private final SendEmailService sendEmailService;
    private final SessionRepository sessionRepository;

    private static final SecureRandom secureRandom = new SecureRandom();

    public LoginService(UserRepository userRepository, UserService userService, SessionRepository sessionRepository,
            PasswordResetRepository passwordResetRepository, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.sessionRepository = sessionRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.sendEmailService = sendEmailService;
    }

    public String login(LoginDto loginDto) {
        String token = createToken();

        LocalDateTime tokenExpire = LocalDateTime.now().plusHours(8);
        User user = userRepository.findByUsername(loginDto.username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se encontro el Usuario");
        } else if (!userService.argon2.verify(user.getPassword(), loginDto.password.toCharArray())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Credenciales Invalidas");
        }
        Sesion sesion = sessionRepository.findByUserId(user.getId());
        if (sesion != null) {
            sesion.setTokenRefresh(token);
            sesion.setDateExpire(tokenExpire);
        } else {
            sesion = new Sesion(
                    token,
                    tokenExpire,
                    user);
        }
        sesion = sessionRepository.save(sesion);
        return token;
    }

    public Map<String, String> getMe(String token) {
        token = token.replace("Bearer ", "");
        Sesion sesion = sessionRepository.findByTokenRefresh(token);
        Map<String, String> user = new HashMap<>();
        user.put("name", sesion.getUser().getName());
        user.put("lastName", sesion.getUser().getLast_name());
        user.put("role", sesion.getUser().getRole().getName());

        return user;
    }

    public String createToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public void tokenEmail(String email) {
        User user = userRepository.findByGmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el Usuario");
        }

        String token = createToken();
        LocalDateTime tokenExpire = LocalDateTime.now().plusMinutes(15);
        PasswordReset passwordSession = passwordResetRepository.findByUser_Id(user.getId());

        if (passwordSession != null) {
            passwordSession.setToken(token);
            passwordSession.setDateExpire(tokenExpire);
        } else {
            passwordSession = new PasswordReset(token, tokenExpire, user);
        }
        try {
            passwordResetRepository.save(passwordSession);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error: " + e.getMessage());
        }

        sendEmailService.sendEmail(email, token);
    }

    public boolean newPassword(LoginPasswordDto loginPasswordDto) {
        PasswordReset passwordReset = passwordResetRepository.findByToken(loginPasswordDto.token());
        if (passwordReset == null || passwordReset.getDateExpire().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expirado");
        }
        if (!loginPasswordDto.newPassword().equals(loginPasswordDto.confirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las contraseñas no coinciden");
        }
        User user = passwordReset.getUser();
        user.setPassword(userService.updatePassword(loginPasswordDto.newPassword()));
        userRepository.save(user);
        return true;
    }
}
