package com.example.coursereview.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationEmail(String email, String token) {
        try {
            String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
            String subject = "Email Verification";
            String body = "Click to verify: " + verificationUrl;

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);

        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
