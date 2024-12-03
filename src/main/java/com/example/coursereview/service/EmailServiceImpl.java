package com.example.coursereview.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${SPRING_MAIL_USERNAME}")
    private String from;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationEmail(String email, String token) {

        LOGGER.info("Attempting to send verification email to: " + email);
        try {
            String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
            String subject = "Email Verification";
            String body = "Click to verify: " + verificationUrl;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(email);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);

        } catch (MailException e) {
            LOGGER.error("Failed to send email: " + e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
