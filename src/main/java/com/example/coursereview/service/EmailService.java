package com.example.coursereview.service;

public interface EmailService {
    void sendVerificationEmail(String email, String token);
}
