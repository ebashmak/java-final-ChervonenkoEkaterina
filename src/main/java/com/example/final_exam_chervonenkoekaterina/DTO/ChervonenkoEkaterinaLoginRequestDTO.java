package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaLoginRequestDTO {
    @NotBlank(message = "Это обязательное поле")
    private String email;

    @NotBlank(message = "Это обязательное поле")
    private String password;
}