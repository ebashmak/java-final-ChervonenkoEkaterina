package com.example.final_exam_chervonenkoekaterina.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChervonenkoEkaterinaRegisterRequestDTO {
    @NotBlank(message = "Это обязательное поле")
    private String username;

    @Email(message = "Некорректный формат почты")
    @NotBlank(message = "Это обязательное поле")
    private String email;

    @NotBlank(message = "Это обязательное поле")
    @Size(min = 6, message = "Пароль должен содержать хотя бы 6 символов")
    private String password;
}