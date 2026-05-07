package com.avant.AvantTechCase.modules.List.Services;

import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.ListEntity;
import com.avant.AvantTechCase.modules.List.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindByIdUseCase {

    @Autowired
    private ListRepository listRepository;

    public ListResponseDTO execute(Long listId, Long userId) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada"));

        if (!list.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Essa lista não é sua!");
        }

        return new ListResponseDTO(
                list.getId(),
                list.getTitle(),
                list.getDescription(),
                list.getUser().getId(),
                list.getCreated_at()
        );
    }
}