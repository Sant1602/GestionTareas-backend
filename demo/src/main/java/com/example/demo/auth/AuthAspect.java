package com.example.demo.auth;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


//Se simula un middleware para la autenticacion de las rutas
@Aspect
@Component
public class AuthAspect {

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(requireAuth)")
    public Object filter(ProceedingJoinPoint joinPoint, RequireAuth requireAuth) throws Throwable {
        String role = requireAuth.role();
        String token = request.getHeader("Authorization");
        if(!(decodeToken(token)).equals(role)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No estas autorizado");
        }
        return joinPoint.proceed();
    }

    private String decodeToken(String token) {
        String check = (token.equals("123"))? "Administrador" : "Cliente";
        //System.out.println(check);
        return check;
    }
}