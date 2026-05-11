package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaLessonDTO {
    private Long id;

    @NotBlank(message = "Заголовок урока обязателен")
    private String title;

    private String videoUrl;
    private Long courseId;
}