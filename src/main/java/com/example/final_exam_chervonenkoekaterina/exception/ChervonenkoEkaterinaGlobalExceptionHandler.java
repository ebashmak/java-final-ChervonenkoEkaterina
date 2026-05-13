package com.example.final_exam_chervonenkoekaterina.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@Hidden
@RestControllerAdvice
public class ChervonenkoEkaterinaGlobalExceptionHandler {

    // обработка ошибок валидации (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // обратка всех остальных исключений
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeErrors(RuntimeException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}