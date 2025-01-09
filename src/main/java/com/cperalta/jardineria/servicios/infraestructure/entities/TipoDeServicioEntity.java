package com.cperalta.jardineria.servicios.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_de_servicio")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDeServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String foto;
}
