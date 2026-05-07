package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaskByIdUseCase {
    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDTO execute(Long taskId, Long userId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!task.getList().getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado");
        }

        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(),
                task.getStatus(), task.getList().getId(), task.getList().getTitle(),
                task.getCreated_at(), task.getDeadline());
    }
}