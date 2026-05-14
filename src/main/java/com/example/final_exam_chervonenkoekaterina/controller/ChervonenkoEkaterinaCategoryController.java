package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaCategoryDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaCategory;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaCategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Категории", description = "Управление категориями курсов (программирование, дизайн и тд)")
public class ChervonenkoEkaterinaCategoryController {

    private final ChervonenkoEkaterinaCategoryService categoryService;

    @Operation(summary = "Создать категорию")
    @ApiResponse(responseCode = "201", description = "Категория успешно создана")
    @PostMapping
    public ResponseEntity<ChervonenkoEkaterinaCategoryDTO> create(@Valid @RequestBody ChervonenkoEkaterinaCategoryDTO dto) {
        ChervonenkoEkaterinaCategoryDTO created = categoryService.createCategory(dto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Получить все категории", description = "Возвращает список всех доступных категорий")
    @GetMapping
    public ResponseEntity<List<ChervonenkoEkaterinaCategoryDTO>> getAll() {
        List<ChervonenkoEkaterinaCategoryDTO> list = categoryService.getAllCategories();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Удалить категорию", description = "Удаляет категорию и все связанные с ней курсы (Cascade)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Редактировать категорию", description = "Изменение названия категории.")
    @PutMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaCategory> updateCategory(
            @PathVariable Long id,
            @RequestParam String newName) {
        return ResponseEntity.ok(categoryService.update(id, newName));
    }
}