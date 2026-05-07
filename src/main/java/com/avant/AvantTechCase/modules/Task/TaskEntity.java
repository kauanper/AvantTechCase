package com.avant.AvantTechCase.modules.Task;

import com.avant.AvantTechCase.modules.List.ListEntity; // Importe a sua ListEntity
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private ListEntity list;

    @CreationTimestamp
    private LocalDateTime created_at;

    private LocalDateTime deadline;
}