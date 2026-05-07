package com.avant.AvantTechCase.modules.List;

import com.avant.AvantTechCase.modules.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "list")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String title;
    private String description;

    @CreationTimestamp
    private LocalDateTime created_at;
}
