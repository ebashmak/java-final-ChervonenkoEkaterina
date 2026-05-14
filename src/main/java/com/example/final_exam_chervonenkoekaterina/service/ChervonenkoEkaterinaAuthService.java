package com.example.final_exam_chervonenkoekaterina.service;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaUserDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaUser;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaUserRepository;
import com.example.final_exam_chervonenkoekaterina.security.ChervonenkoEkaterinaJwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaAuthService {
    private final ChervonenkoEkaterinaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChervonenkoEkaterinaEmailService emailService;
    private final ChervonenkoEkaterinaJwtUtils jwtUtils;

    public ChervonenkoEkaterinaUserDTO register(String username, String email, String password) {
        log.info("Attempting to register user with email: {}", email);

        if (userRepository.existsByEmail(email)) {
            log.warn("Registration failed: email {} already exists", email);
            throw new RuntimeException("Email already exists!");
        }

        ChervonenkoEkaterinaUser user = new ChervonenkoEkaterinaUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of("ROLE_USER"));

        ChervonenkoEkaterinaUser savedUser = userRepository.save(user);
        log.info("User {} successfully saved to PostgreSQL", email);

        // Асинхронный вызов
        emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getUsername());

        // Маппинг в DTO
        ChervonenkoEkaterinaUserDTO dto = new ChervonenkoEkaterinaUserDTO();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        dto.setEmail(savedUser.getEmail());
        dto.setRoles(savedUser.getRoles());

        return dto;
    }

    public Map<String, String> login(String email, String password) {
        log.info("Login attempt for user: {}", email);

        ChervonenkoEkaterinaUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Login failed: user {} not found", email);
                    return new RuntimeException("User not found");
                });

        if (passwordEncoder.matches(password, user.getPassword())) {
            log.info("User {} authenticated successfully", email);

            emailService.sendLoginNotification(email);

            String accessToken = jwtUtils.generateAccessToken(email);
            String refreshToken = jwtUtils.generateRefreshToken(email);

            return Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            );
        } else {
            log.warn("Login failed: invalid password for user {}", email);
            throw new RuntimeException("Invalid password");
        }
    }

    public Map<String, String> refreshTokens(String refreshToken) {
        if (jwtUtils.validateToken(refreshToken)) {
            String email = jwtUtils.getEmailFromToken(refreshToken);
            log.info("Refreshing tokens for user: {}", email);

            return Map.of(
                    "accessToken", jwtUtils.generateAccessToken(email),
                    "refreshToken", jwtUtils.generateRefreshToken(email)
            );
        }
        log.error("Refresh token validation failed");
        throw new RuntimeException("Invalid refresh token");
    }
}