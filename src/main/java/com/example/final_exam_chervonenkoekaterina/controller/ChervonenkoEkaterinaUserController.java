package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaUserDTO;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Управление пользователями системы (просмотр, редактирование, удаление)")
public class ChervonenkoEkaterinaUserController {

    private final ChervonenkoEkaterinaUserService userService;

    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех зарегистрированных пользователей")
    @GetMapping
    public ResponseEntity<List<ChervonenkoEkaterinaUserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Пользователь найден")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @GetMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaUserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Редактировать пользователя", description = "Изменение имени пользователя (username) и email")
    @PutMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaUserDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ChervonenkoEkaterinaUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @Operation(summary = "Удалить пользователя", description = "Полное удаление пользователя из системы")
    @ApiResponse(responseCode = "244", description = "Пользователь удален")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}