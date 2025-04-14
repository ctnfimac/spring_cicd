package com.microservice.users.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "rol")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", unique = true)
    private String description;
}
