package com.avant.AvantTechCase.modules.List;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.User.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list")
public class ListController {

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody @Valid ListRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        //NewsResponseDTO responseDTO  = craeteNewsUseCase.execute(userId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
