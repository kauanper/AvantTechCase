package com.avant.AvantTechCase.modules.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaksRepository extends JpaRepository<Task, Long> {
}
