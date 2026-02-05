package com.waldston.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(min = 3, max = 50)
        String name) {
}
