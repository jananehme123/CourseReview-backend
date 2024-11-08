package com.example.coursereview.controller;

import com.example.coursereview.model.User;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.service.EmailServiceImpl;
import com.example.coursereview.utils.JwtUtil;
import com.example.coursereview.utils.PasswordUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private PasswordUtil passwordUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) throws MessagingException {
        if (!user.getEmail().endsWith("@mail.aub.edu") && !user.getEmail().endsWith("@aub.edu.lb")) {
            return "Invalid email domain.";
        }

        user.setPassword(passwordUtil.encodePassword(user.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        emailService.sendVerificationEmail(user.getEmail(), token);

        return "Verification email sent!";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token) {
        String email = jwtUtil.validateToken(token);
        if (email == null) {
            return "Invalid or expired token.";
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setVerified(true);
            userRepository.save(user);
            return "Email verified successfully!";
        }

        return "User not found.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordUtil.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401).body("Invalid email or password.");
    }
}
