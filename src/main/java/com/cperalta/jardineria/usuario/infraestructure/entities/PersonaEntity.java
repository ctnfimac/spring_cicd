package com.cperalta.jardineria.usuario.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;

    private String contrasenia;

    @Column(name = "token_activacion")
    private String tokenActivacion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private RoleEntity role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private EstadoEntity estado;
}
