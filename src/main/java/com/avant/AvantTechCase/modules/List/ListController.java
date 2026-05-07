package com.avant.AvantTechCase.modules.List;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.Services.*;
import com.avant.AvantTechCase.modules.User.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("list")
public class ListController {

    @Autowired
    CreateListUseCase createListUseCase;

    @Autowired
    FindByIdUseCase findByIdUseCase;

    @Autowired
    DeleteByIdUseCase deleteByIdUseCase;

    @Autowired
    UpdateByIdUseCase updateByIdUseCase;

    @Autowired
    ListAllByUserUseCase listAllByUserUseCase;

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody @Valid ListRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        ListResponseDTO responseDTO  = createListUseCase.execute(dto, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ListResponseDTO>> listAll() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var lists = listAllByUserUseCase.execute(user.getId());
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListResponseDTO> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity user
    ) {
        var response = findByIdUseCase.execute(id, user.getId());
        return ResponseEntity.ok(response);
    }


    // Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteByIdUseCase.execute(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    // Editar por ID
    @PutMapping("/{id}")
    public ResponseEntity<ListResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ListRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var updated = updateByIdUseCase.execute(id, user.getId(), dto);
        return ResponseEntity.ok(updated);
    }

}