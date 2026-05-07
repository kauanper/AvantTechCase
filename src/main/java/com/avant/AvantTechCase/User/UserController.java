package com.avant.AvantTechCase.User;

import com.avant.AvantTechCase.User.DTOs.UserRegisterDTO;
import com.avant.AvantTechCase.User.DTOs.UserResponseDTO;
import com.avant.AvantTechCase.User.services.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO data) {
        UserResponseDTO dto = createUserUseCase.execute(data);

        if(dto == null) {
            return ResponseEntity.badRequest().body(null);
        }else{
            return ResponseEntity.ok().body(dto);
        }
    }
}