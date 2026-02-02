package com.waldston.ecommerce.infra; // ajuste para seu pacote de infra

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }


    public record ValidationError(String field, String message) {}
}