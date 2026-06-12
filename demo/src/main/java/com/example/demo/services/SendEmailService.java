package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService implements ISendEmailService {

    private final JavaMailSender mailSender;

    public SendEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean sendEmail(String to, String token) {
        try {

            String subject = "Recuperacion de contraseña";
            String text = """
                    Estimado usuario,

                    Hemos recibido una solicitud para restablecer la contraseña de tu cuenta. Si no realizaste esta
                    solicitud, puedes ignorar este correo electrónico.
                    Haz clic en el siguiente enlace para restablecer tu contraseña:
                    http://localhost:3000/auth/new-password?token=%s""".formatted(token);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("santialvarez1602@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
