package com.avant.AvantTechCase.modules.List.Services;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.ListEntity;
import com.avant.AvantTechCase.modules.List.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateByIdUseCase {

    @Autowired
    private ListRepository listRepository;

    public ListResponseDTO execute(Long listId, Long userId, ListRequestDTO dto) {

        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));


        if (!list.getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado: você não pode editar uma lista que não lhe pertence");
        }


        list.setTitle(dto.title());
        list.setDescription(dto.description());


        ListEntity updatedList = listRepository.save(list);


        return new ListResponseDTO(
                updatedList.getId(),
                updatedList.getTitle(),
                updatedList.getDescription(),
                updatedList.getUser().getId(),
                updatedList.getCreated_at()
        );
    }
}