package com.cperalta.jardineria.usuario.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", unique = true)
    private String description;

}
