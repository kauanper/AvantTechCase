package com.avant.AvantTechCase.modules.Task.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TaskRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String title,

        String description,

        @NotNull(message = "O ID da lista é obrigatório")
        Long listId,

        LocalDateTime deadline
) {
}