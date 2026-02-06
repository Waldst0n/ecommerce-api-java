package com.waldston.ecommerce.service;

import com.waldston.ecommerce.dto.category.CategoryRequestDTO;
import com.waldston.ecommerce.dto.category.CategoryResponseDTO;
import com.waldston.ecommerce.exception.category.CategoryNotFoundException;
import com.waldston.ecommerce.model.Category;
import com.waldston.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryResponseDTO> findAll(Pageable pagination) {
        return categoryRepository.findAll(pagination).map(category -> new CategoryResponseDTO(category.getId(), category.getName(), category.getDeletedAt()));
    }

    public CategoryResponseDTO findById(UUID id) {
        Category response = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada!"));
        return new CategoryResponseDTO(response.getId(), response.getName(), response.getDeletedAt());
    }

    public CategoryResponseDTO create(CategoryRequestDTO data) {

        Category category = new Category();
        category.setName(data.name());
        Category newCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(newCategory.getId(), newCategory.getName(), newCategory.getDeletedAt());



    }

    public void remove(UUID id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada!"));

            category.setDeletedAt(LocalDateTime.now());

            categoryRepository.save(category);
        }

    }
