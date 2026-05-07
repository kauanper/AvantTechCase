package com.avant.AvantTechCase.modules.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.list.id = :listId AND t.list.user.id = :userId " +
            "ORDER BY CASE t.status " +
            "WHEN 'PENDENTE' THEN 1 " +
            "WHEN 'EM_ANDAMENTO' THEN 2 " +
            "WHEN 'CONCLUIDA' THEN 3 ELSE 4 END")
    List<TaskEntity> findByListIdAndUserIdCustomOrder(Long listId, Long userId);
}