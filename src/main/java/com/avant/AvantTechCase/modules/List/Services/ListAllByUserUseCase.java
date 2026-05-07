package com.avant.AvantTechCase.modules.List.Services;

import com.avant.AvantTechCase.modules.List.DTOs.ListResponseDTO;
import com.avant.AvantTechCase.modules.List.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllByUserUseCase {

    @Autowired
    private ListRepository listRepository;

    public List<ListResponseDTO> execute(Long userId) {
        return listRepository.findByUserId(userId)
                .stream()
                .map(list -> new ListResponseDTO(
                        list.getId(),
                        list.getTitle(),
                        list.getDescription(),
                        list.getUser().getId(),
                        list.getCreated_at()
                ))
                .collect(Collectors.toList());
    }
}