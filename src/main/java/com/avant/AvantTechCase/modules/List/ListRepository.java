package com.avant.AvantTechCase.modules.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ListRepository extends JpaRepository<ListEntity, Long> {
    @EntityGraph(attributePaths = {"tasks"})
    List<ListEntity> findByUserId(Long userId);
}
