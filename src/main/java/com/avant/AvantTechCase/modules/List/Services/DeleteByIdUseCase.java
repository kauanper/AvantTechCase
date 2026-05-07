package com.avant.AvantTechCase.modules.List.Services;

import com.avant.AvantTechCase.modules.List.ListEntity;
import com.avant.AvantTechCase.modules.List.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdUseCase {

    @Autowired
    private ListRepository listRepository;

    public void execute(Long listId, Long userId) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        if (!list.getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado: você não pode deletar uma lista que não te pertence");
        }

        listRepository.delete(list);
    }
}