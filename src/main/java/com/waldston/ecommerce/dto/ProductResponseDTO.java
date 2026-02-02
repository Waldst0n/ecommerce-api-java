package com.waldston.ecommerce.dto;

import com.waldston.ecommerce.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, BigDecimal price, Category category) {
}
