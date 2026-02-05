package com.waldston.ecommerce.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO(
        @NotBlank(message = "O nome do produto não pode estar em branco")
        @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 100 caracteres")
        String name,

        @NotNull(message = "O Preço é obrigatório")
        @Positive(message = "O preço deve ser um valor positivo")
        BigDecimal price,

        @NotNull UUID category_id
) {}
