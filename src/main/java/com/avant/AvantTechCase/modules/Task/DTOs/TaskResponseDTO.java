package com.avant.AvantTechCase.modules.Task.DTOs;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Long listId,
        String listTitle,
        LocalDateTime createdAt,
        LocalDateTime deadline
) {
}