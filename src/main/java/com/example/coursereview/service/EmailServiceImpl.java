package com.example.coursereview.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
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

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationEmail(String email, String token) {
        LOGGER.info("Attempting to send verification email to: " + email);
        try {
            String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
            String subject = "Email Verification";
            String body = "Click to verify: " + verificationUrl;

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom(from);

            mailSender.send(message);

        } catch (MailException | MessagingException e) {
            LOGGER.error("Failed to send email: " + e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
