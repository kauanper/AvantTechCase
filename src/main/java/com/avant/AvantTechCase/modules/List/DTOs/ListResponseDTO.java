package com.avant.AvantTechCase.modules.List.DTOs;

import java.time.LocalDateTime;

public record ListResponseDTO(
        Long id,
        String title,
        String description,
        Long userId,
        LocalDateTime createdAt
) {
}