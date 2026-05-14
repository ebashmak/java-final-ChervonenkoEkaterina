package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChervonenkoEkaterinaCategoryDTO {
    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    private String name;
}