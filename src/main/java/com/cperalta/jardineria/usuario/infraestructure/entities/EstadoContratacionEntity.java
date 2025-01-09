package com.cperalta.jardineria.usuario.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_contratacion")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoContratacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String descripcion;
}
