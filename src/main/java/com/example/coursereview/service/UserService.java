package com.example.coursereview.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    void registerUser(String email, String rawPassword);

    boolean authenticate(String email, String rawPassword);
}
