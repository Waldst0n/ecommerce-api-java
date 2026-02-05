package com.waldston.ecommerce.dto.category;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponseDTO(UUID id, String name, LocalDateTime deleted_at) {
}
