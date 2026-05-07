package com.avant.AvantTechCase.User;

import com.avant.AvantTechCase.User.DTOs.UserRegisterDTO;
import com.avant.AvantTechCase.User.DTOs.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO data) {
        UserResponseDTO dto = new UserResponseDTO(1L, data.name(), data.login(), data.password());
        return ResponseEntity.ok(dto);
    }
}