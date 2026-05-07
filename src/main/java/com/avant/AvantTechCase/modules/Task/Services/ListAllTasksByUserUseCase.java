package com.avant.AvantTechCase.modules.Task.Services;

import com.avant.AvantTechCase.modules.List.ListRepository;
import com.avant.AvantTechCase.modules.Task.DTOs.ListWithTasksResponseDTO;
import com.avant.AvantTechCase.modules.Task.DTOs.TaskResponseDTO;
import com.avant.AvantTechCase.modules.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllTasksByUserUseCase {
    @Autowired
    private ListRepository listRepository;

    public List<ListWithTasksResponseDTO> execute(Long userId) {

        var lists = listRepository.findByUserId(userId);

        return lists.stream().map(list -> {
            var taskDTOs = list.getTasks().stream()
                    .map(t -> new TaskResponseDTO(
                            t.getId(), t.getTitle(), t.getDescription(),
                            t.getStatus(), list.getId(), list.getTitle(),
                            t.getCreated_at(), t.getDeadline()))
                    .toList();

            return new ListWithTasksResponseDTO(list.getId(), list.getTitle(), taskDTOs);
        }).toList();
    }
}
