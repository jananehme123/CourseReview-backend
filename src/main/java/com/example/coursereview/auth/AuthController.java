package com.example.coursereview.auth;

import com.example.coursereview.model.User;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.service.EmailServiceImpl;
import com.example.coursereview.utils.JwtUtil;
import com.example.coursereview.utils.PasswordUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private PasswordUtil passwordUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        String email = jwtUtil.getSubject(token);
        if (email == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setVerified(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }

        return ResponseEntity.status(404).body("User not found.");
    }
}
