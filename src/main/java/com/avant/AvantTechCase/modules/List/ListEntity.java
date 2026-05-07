package com.avant.AvantTechCase.modules.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "list")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListEntity {

    @Id
    long id;

    String title;
    String description;
    private LocalDateTime created_at;
}
