package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaCourseDTO {
    private Long id;

    @NotBlank(message = "Название курса обязательно")
    private String title;

    private String description;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    private Long categoryId;
    private String categoryName;
}
