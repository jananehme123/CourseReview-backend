package com.example.coursereview.controller;

import com.example.coursereview.model.Role;
import com.example.coursereview.model.User;
import com.example.coursereview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/make-admin/{id}")
    public ResponseEntity<String> makeUserAdmin(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok("User promoted to admin");
    }
}
