package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaCourseDTO {
    private Long id;

    private String title;

    private String description;

    private Double price;

    private Long categoryId;
    private String categoryName;
}
