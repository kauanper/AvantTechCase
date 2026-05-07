package com.avant.AvantTechCase.modules.User.services;

import com.avant.AvantTechCase.modules.User.DTOs.UserRegisterDTO;
import com.avant.AvantTechCase.modules.User.DTOs.UserResponseDTO;
import com.avant.AvantTechCase.modules.User.UserEntity;
import com.avant.AvantTechCase.modules.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO execute(UserRegisterDTO data) {
        if(userRepository.findByLogin(data.login()) != null) {
            return null;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserEntity user = new UserEntity();
        user.setName(data.name());
        user.setLogin(data.login());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                data.password()
        );
    }
}
