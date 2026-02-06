package com.waldston.ecommerce.infra; // ajuste para seu pacote de infra

import com.waldston.ecommerce.exception.category.CategoryNotFoundException;
import com.waldston.ecommerce.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrors> handleProductErrors(ProductNotFoundException ex) {
        ProductErrors errors = new ProductErrors(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryErrors> handleCategoryErrors(CategoryNotFoundException ex) {
        CategoryErrors errors = new CategoryErrors(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }



    public record ValidationError(String field, String message) {}
    public record ProductErrors(String message) {}
    public record CategoryErrors(String message) {}
}