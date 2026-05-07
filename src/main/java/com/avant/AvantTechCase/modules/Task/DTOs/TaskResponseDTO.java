package com.avant.AvantTechCase.modules.Task.DTOs;

import com.avant.AvantTechCase.modules.Task.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Long listId,
        String listTitle,
        LocalDateTime createdAt,
        LocalDateTime deadline
) {}