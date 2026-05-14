package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaLoginRequestDTO;
import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaRegisterRequestDTO;
import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaUserDTO;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Регистрация, вход и обновление токенов доступа (JWT)")
public class ChervonenkoEkaterinaAuthController {
    private final ChervonenkoEkaterinaAuthService authService;

    @Operation(summary = "Регистрация пользователя", description = "Создает новый аккаунт студента")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно создан")
    @PostMapping("/register")
    public ResponseEntity<ChervonenkoEkaterinaUserDTO> register(@Valid @RequestBody ChervonenkoEkaterinaRegisterRequestDTO request) {
        ChervonenkoEkaterinaUserDTO user = authService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Авторизация (Login)", description = "Принимает email и пароль, возвращает Access и Refresh токены")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody ChervonenkoEkaterinaLoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword()));
    }

    @Operation(summary = "Обновление токена", description = "Позволяет получить новый Access Token по действующему Refresh Token")
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return ResponseEntity.ok(authService.refreshTokens(refreshToken));
    }
}