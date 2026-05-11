package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaReviewDTO {
    private String id;
    private Long courseId;
    private String userEmail;

    @NotBlank(message = "Комментарий не может быть пустым")
    private String comment;

    @Min(1) @Max(5)
    private int rating;
}