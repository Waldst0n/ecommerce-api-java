package com.waldston.ecommerce.controller;

import com.waldston.ecommerce.dto.category.CategoryRequestDTO;
import com.waldston.ecommerce.dto.category.CategoryResponseDTO;
import com.waldston.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Page<CategoryResponseDTO>> findAll(@PageableDefault( sort = "name") Pageable pagination) {

        return ResponseEntity.ok(categoryService.findAll(pagination));

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable UUID id) {
        CategoryResponseDTO category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable UUID id) {
        categoryService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoryRequestDTO dto) {
        CategoryResponseDTO category = categoryService.update(id, dto);

        return  ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO dto) {
        CategoryResponseDTO newCategory = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
