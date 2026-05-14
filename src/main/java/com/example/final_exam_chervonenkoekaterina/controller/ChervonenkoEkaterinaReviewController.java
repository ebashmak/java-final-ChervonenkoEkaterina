package com.example.final_exam_chervonenkoekaterina.controller;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaReviewDTO;
import com.example.final_exam_chervonenkoekaterina.service.ChervonenkoEkaterinaReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Отзывы", description = "Управление отзывами пользователей (хранятся в MongoDB)")
public class ChervonenkoEkaterinaReviewController {

    private final ChervonenkoEkaterinaReviewService reviewService;

    @PostMapping
    @Operation(summary = "Написать отзыв", description = "Сохраняет новый отзыв о курсе в MongoDB")
    public ResponseEntity<ChervonenkoEkaterinaReviewDTO> addReview(@RequestBody ChervonenkoEkaterinaReviewDTO dto) {
        return ResponseEntity.ok(reviewService.addReview(dto));
    }

    @Operation(summary = "Получить отзывы курса", description = "Возвращает список всех отзывов для конкретного курса по его ID")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ChervonenkoEkaterinaReviewDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(reviewService.getReviewsByCourse(courseId));
    }
}