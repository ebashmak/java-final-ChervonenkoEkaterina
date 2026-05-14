package com.example.final_exam_chervonenkoekaterina.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Добавили для логов
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaEmailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendWelcomeEmail(String toEmail, String name) {
        log.info("Starting asynchronous email sending process for: {}", toEmail);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("chrvn.katya@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Registration Successful");
            message.setText("Hello " + name + "! Welcome to our system.");
            mailSender.send(message);
            log.info("Email successfully sent to: {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
    }

    @Async
    public void sendLoginNotification(String toEmail) {
        log.info("Asynchronous sending of login notification to: {}", toEmail);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("chrvn.katya@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Security Alert: New Login Detected");
            message.setText("Hello!\n\nThis is a notification that a new login was detected for your account. " +
                    "If this was not you, please contact our support team immediately.\n\nBest regards,\nCourse System Team");

            mailSender.send(message);
            log.info("Login notification successfully sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send login notification: {}", e.getMessage());
        }
    }

}