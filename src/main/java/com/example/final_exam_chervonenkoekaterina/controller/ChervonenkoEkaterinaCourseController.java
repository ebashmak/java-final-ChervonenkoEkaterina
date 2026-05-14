package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaCourseDTO;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name = "Курсы", description = "Управление каталогом курсов")
public class ChervonenkoEkaterinaCourseController {

    private final ChervonenkoEkaterinaCourseService courseService;

    @Operation(summary = "Добавить новый курс", description = "Создает новый курс.")
    @PostMapping
    public ResponseEntity<ChervonenkoEkaterinaCourseDTO> create(@Valid @RequestBody ChervonenkoEkaterinaCourseDTO dto) {
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    // все курсф с пагинацией и поиском
    // api/courses?title=Java&page=0&size=5
    @Operation(summary = "Получить все курсы", description = "Возвращает список курсов с поддержкой поиска по названию, пагинации и сортировки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успешно получен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @GetMapping
    public ResponseEntity<Page<ChervonenkoEkaterinaCourseDTO>> getAll(
            @Parameter(description = "Поиск по названию курса (необязательно)", example = "Java")
            @RequestParam(required = false) String title,

            @ParameterObject
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getAllCourses(title, pageable));
    }

    @Operation(summary = "Получить курс по айди", description = "Возвращает курс по айди.")
    @GetMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaCourseDTO> getById(@Parameter(description = "Уникальный идентификатор курса", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @Operation(summary = "Обновить данные курса", description = "Позволяет изменить название, описание или цену.")
    @PutMapping("/{id}")
    public ResponseEntity<ChervonenkoEkaterinaCourseDTO> update(
            @Parameter(description = "ID курса для обновления", example = "1") @PathVariable Long id,
            @Valid @RequestBody ChervonenkoEkaterinaCourseDTO dto) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }
}