package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaCategoryDTO;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Управление категориями курсов (программирование, дизайн и тдтп)")
public class ChervonenkoEkaterinaCategoryController {

    private final ChervonenkoEkaterinaCategoryService categoryService;

    @Operation(summary = "Создать категорию", description = "Создание новой категории. **Требуется войти в систему**.")
    @ApiResponse(responseCode = "201", description = "Категория успешно создана")
    @ApiResponse(
            responseCode = "403",
            description = "Доступ запрещен: требуется войти в систему",
            content = @Content
    )
    @PostMapping
    public ResponseEntity<ChervonenkoEkaterinaCategoryDTO> create(@Valid @RequestBody ChervonenkoEkaterinaCategoryDTO dto) {
        ChervonenkoEkaterinaCategoryDTO created = categoryService.createCategory(dto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Получить все категории", description = "Возвращает список всех доступных категорий. **Требуется войти в систему**.")
    @ApiResponse(responseCode = "200", description = "Список категорий успешно получен")
    @ApiResponse(
            responseCode = "403",
            description = "Доступ запрещен: требуется войти в систему",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<ChervonenkoEkaterinaCategoryDTO>> getAll() {
        List<ChervonenkoEkaterinaCategoryDTO> list = categoryService.getAllCategories();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Удалить категорию", description = "Удаляет категорию и все связанные с ней курсы (Cascade). **Требуется войти в систему**.")
    @ApiResponse(responseCode = "204", description = "Категория успешно удалена")
    @ApiResponse(
            responseCode = "403",
            description = "Доступ запрещен: требуется войти в систему",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Редактировать категорию", description = "Изменение названия категории. **Требуется войти в систему**.")
    @ApiResponse(responseCode = "200", description = "Название успешно изменено")
    @ApiResponse(
            responseCode = "403",
            description = "Доступ запрещен: требуется войти в систему",
            content = @Content
    )
    @PutMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaCategoryDTO> updateCategory(
            @PathVariable Long id,
            @RequestParam String newName) {
        return ResponseEntity.ok(categoryService.update(id, newName));
    }
}