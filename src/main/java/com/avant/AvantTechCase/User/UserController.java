package com.avant.AvantTechCase.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserController {

    @GetMapping("/lombok")
    public String testLombok() {
        // Testando o @AllArgsConstructor
        UserEntity user = new UserEntity(1L, "kauan_admin", "senha123");

        // Testando o @Data (Getter)
        return "Lombok funcionando! Usuário: " + user.getLogin() + " com ID: " + user.getId();
    }
}