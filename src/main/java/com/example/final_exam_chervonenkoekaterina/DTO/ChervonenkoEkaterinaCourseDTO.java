package com.example.final_exam_chervonenkoekaterina.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO для передачи данных о курсе")
public class ChervonenkoEkaterinaCourseDTO {
    private Long id;

    @Schema(description = "Название курса", example = "Java Spring Boot для студентов")
    @NotBlank(message = "Название курса обязательно")
    private String title;

    private String description;

    @Schema(description = "Цена курса", example = "299.99")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    private Long categoryId;
    private String categoryName;
}
