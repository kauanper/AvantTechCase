package com.avant.AvantTechCase.modules.User;

import com.avant.AvantTechCase.modules.User.DTOs.UserRegisterDTO;
import com.avant.AvantTechCase.modules.User.DTOs.UserResponseDTO;
import com.avant.AvantTechCase.modules.User.services.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/profile")
    public ResponseEntity getPerfil() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long id = user.getId();
        String login = user.getLogin();

        return ResponseEntity.ok("O ID do usuário logado é: " + id);
    }
}