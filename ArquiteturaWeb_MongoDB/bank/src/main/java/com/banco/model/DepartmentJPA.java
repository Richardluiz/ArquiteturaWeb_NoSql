package com.banco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentJPA{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message = "O título não pode estar vazio")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String description;

    private boolean active;
}