package com.cperalta.jardineria.contratacion.infraestructure.entities;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trabajo_realizado")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajoRealizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foto;

    @Column(unique = true)
    private String descripcion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jardinero_id", referencedColumnName = "id")
    private JardineroEntity jardinero;

}
