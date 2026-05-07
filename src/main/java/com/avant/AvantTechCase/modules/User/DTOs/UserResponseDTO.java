package com.avant.AvantTechCase.modules.User.DTOs;

import lombok.Data;

public record UserResponseDTO(Long id, String name, String email, String password) {
}
