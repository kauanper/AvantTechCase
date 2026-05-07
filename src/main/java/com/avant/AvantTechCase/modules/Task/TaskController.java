package com.avant.AvantTechCase.modules.Task;

import com.avant.AvantTechCase.modules.Task.DTOs.TaskRequestDTO;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.Services.*;
import com.avant.AvantTechCase.modules.User.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private CreateTaskUseCase createTaskUseCase;

    @Autowired
    private ListAllTasksByUserUseCase listAllTasksByUserUseCase;

    @Autowired
    private FindTaskByIdUseCase findTaskByIdUseCase;

    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Autowired
    private DeleteTaskUseCase deleteTaskUseCase;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskResponseDTO response = createTaskUseCase.execute(dto, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{listId}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> listTasksByList(
            @PathVariable Long listId,
            @AuthenticationPrincipal UserEntity user
    ) {
        var response = listAllTasksByUserUseCase.execute(listId, user.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var response = findTaskByIdUseCase.execute(id, user.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TaskRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var response = updateTaskUseCase.execute(id, user.getId(), dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteTaskUseCase.execute(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}