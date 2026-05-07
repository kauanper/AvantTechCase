package com.avant.AvantTechCase.modules.List.Services;

import com.avant.AvantTechCase.modules.List.DTOs.ListRequestDTO;
import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.ListEntity;
import com.avant.AvantTechCase.modules.List.ListRepository;
import com.avant.AvantTechCase.modules.User.UserEntity;
import com.avant.AvantTechCase.modules.User.UserRepository; // Importe seu repositório de usuário
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateListUseCase {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserRepository userRepository;

    public ListResponseDTO execute(ListRequestDTO listRequestDTO, Long userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ListEntity listEntity = new ListEntity();
        listEntity.setTitle(listRequestDTO.title());
        listEntity.setDescription(listRequestDTO.description());
        listEntity.setUser(user);

        ListEntity savedList = listRepository.save(listEntity);

        return new ListResponseDTO(
                savedList.getId(),
                savedList.getTitle(),
                savedList.getDescription(),
                savedList.getUser().getId(),
                savedList.getCreated_at()
        );
    }
}