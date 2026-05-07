package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import com.avant.AvantTechCase.modules.Task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllTasksByUserUseCase {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> execute(Long listId, Long userId) {
        return taskRepository.findByListIdAndUserIdCustomOrder(listId, userId).stream()
                .map(t -> new TaskResponseDTO(
                        t.getId(), t.getTitle(), t.getDescription(),
                        t.getStatus(), t.getList().getId(), t.getList().getTitle(),
                        t.getCreated_at(), t.getDeadline()))
                .toList();
    }
}
