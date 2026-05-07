package com.avant.AvantTechCase.modules.List;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.Services.CreateListUseCase;
import com.avant.AvantTechCase.modules.User.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CreateListUseCase createListUseCase;

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody @Valid ListRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        ListResponseDTO responseDTO  = createListUseCase.execute(dto, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
