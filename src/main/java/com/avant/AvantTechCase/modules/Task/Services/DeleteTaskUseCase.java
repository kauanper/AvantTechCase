package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;

    public void execute(Long taskId, Long userId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!task.getList().getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado");
        }

        taskRepository.delete(task);
    }
}
