package com.example.coursereview.auth;

import com.example.coursereview.model.Role;
import com.example.coursereview.repository.TokenRepository;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.service.EmailService;
import com.example.coursereview.utils.JwtUtil;
import com.example.coursereview.model.Token;
import com.example.coursereview.model.TokenType;
import lombok.RequiredArgsConstructor;
import com.example.coursereview.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);


    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .verified(false)
                .build();
        if (!user.getEmail().endsWith("@mail.aub.edu") && !user.getEmail().endsWith("@aub.edu.lb")) {
            throw new RuntimeException("Invalid email domain.");
        }
        else {
            repository.save(user);
        }
        var jwt = jwtUtil.generateToken(user);
        SaveUserToken(user, jwt);
//        try {
//            emailService.sendVerificationEmail(user.getEmail(), jwt);
//        } catch (Exception e) {
//            LOGGER.error("Failed to send verification email, but user was registered: " + e.getMessage());
//        }
        return AuthenticationResponse.builder()
                .token(jwt)
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e;
        }
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtUtil.generateToken(user);
        revokeAllUserTokens(user);
        SaveUserToken(user, jwt);
//        if (!user.isVerified()) {
//            try {
//                emailService.sendVerificationEmail(user.getEmail(), jwt);
//            } catch (Exception e) {
//                LOGGER.error("Failed to send verification email, but user was registered: " + e.getMessage());
//            }
//            throw new RuntimeException("User account is not verified");
//        }
        return AuthenticationResponse.builder()
                .token(jwt)
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    private void SaveUserToken(User user, String jwt) {
        var token = Token.builder()
                .user(user)
                .token(jwt)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);

    }
}
