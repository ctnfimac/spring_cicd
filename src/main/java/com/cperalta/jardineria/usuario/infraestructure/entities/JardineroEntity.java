package com.cperalta.jardineria.usuario.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "jardinero")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JardineroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String telefono;

    @Column(unique = true)
    private String email;

    private String presentacion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private PersonaEntity persona;
}
