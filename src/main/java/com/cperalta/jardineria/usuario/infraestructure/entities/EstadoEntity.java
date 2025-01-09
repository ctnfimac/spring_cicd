package com.cperalta.jardineria.usuario.infraestructure.entities;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String descripcion;

}
