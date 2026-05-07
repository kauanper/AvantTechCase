package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.Task.DTOs.TaskRequestDTO;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskEntity;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import com.avant.AvantTechCase.modules.Task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired private com.avant.AvantTechCase.modules.List.ListRepository listRepository;

    public TaskResponseDTO execute(TaskRequestDTO dto, Long userId) {
        var list = listRepository.findById(dto.listId())
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        if (!list.getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado à lista");
        }

        TaskEntity task = new TaskEntity();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status() != null ? dto.status() : TaskStatus.PENDENTE);
        task.setDeadline(dto.deadline());
        task.setList(list);

        var saved = taskRepository.save(task);
        return toDTO(saved);
    }

    private TaskResponseDTO toDTO(TaskEntity t) {
        return new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(),
                t.getStatus(), t.getList().getId(), t.getList().getTitle(),
                t.getCreated_at(), t.getDeadline());
    }
}
