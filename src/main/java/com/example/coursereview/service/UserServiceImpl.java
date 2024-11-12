package com.example.coursereview.service;

import com.example.coursereview.model.User;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public void registerUser(String email, String rawPassword) {
        String hashedPassword = passwordUtil.encodePassword(rawPassword);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public boolean authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return passwordUtil.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}