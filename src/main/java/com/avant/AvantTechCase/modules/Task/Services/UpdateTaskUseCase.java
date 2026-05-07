package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.Task.DTOs.TaskRequestDTO;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDTO execute(Long taskId, Long userId, TaskRequestDTO dto) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!task.getList().getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado");
        }

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setDeadline(dto.deadline());

        var updated = taskRepository.save(task);
        return new TaskResponseDTO(updated.getId(), updated.getTitle(), updated.getDescription(),
                updated.getStatus(), updated.getList().getId(), updated.getList().getTitle(),
                updated.getCreated_at(), updated.getDeadline());
    }
}