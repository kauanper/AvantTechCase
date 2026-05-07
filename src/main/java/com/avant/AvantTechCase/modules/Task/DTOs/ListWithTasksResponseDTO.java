package com.avant.AvantTechCase.modules.Task.DTOs;

import java.util.List;

public record ListWithTasksResponseDTO(
        Long id,
        String title,
        List<TaskResponseDTO> tasks
) {}