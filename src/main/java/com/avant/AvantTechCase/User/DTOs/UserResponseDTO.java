package com.avant.AvantTechCase.User.DTOs;

import lombok.Data;

public record UserResponseDTO(Long id, String name, String email, String password) {
}
