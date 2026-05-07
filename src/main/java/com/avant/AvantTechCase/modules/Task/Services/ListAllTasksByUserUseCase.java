package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.List.ListEntity;
import com.avant.AvantTechCase.modules.List.ListRepository;
import com.avant.AvantTechCase.modules.Task.DTOs.ListWithTasksResponseDTO;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import com.avant.AvantTechCase.modules.Task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllTasksByUserUseCase {

    @Autowired
    private ListRepository listRepository;

    public List<ListWithTasksResponseDTO> execute(Long userId) {

        List<ListEntity> userLists = listRepository.findByUserId(userId);

        return userLists.stream().map(list -> {

            // 2. Transforma e ordena as tarefas da lista
            List<TaskResponseDTO> sortedTasks = list.getTasks().stream()
                    .sorted((t1, t2) -> {
                        // Atribui pesos para a ordenação: PENDENTE(1), EM_ANDAMENTO(2), CONCLUIDA(3)
                        return Integer.compare(getStatusWeight(t1.getStatus()), getStatusWeight(t2.getStatus()));
                    })
                    .map(t -> new TaskResponseDTO(
                            t.getId(), t.getTitle(), t.getDescription(),
                            t.getStatus(), list.getId(), list.getTitle(),
                            t.getCreated_at(), t.getDeadline()))
                    .toList();

            return new ListWithTasksResponseDTO(list.getId(), list.getTitle(), sortedTasks);
        }).toList();
    }

    // Helper method para definir a prioridade visual
    private int getStatusWeight(TaskStatus status) {
        if (status == null) return 4;
        return switch (status) {
            case PENDENTE -> 1;
            case EM_ANDAMENTO -> 2;
            case CONCLUIDA -> 3;
            default -> 4;
        };
    }
}
