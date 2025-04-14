package com.microservice.users.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "nombre")
    private String name;

    @Column( name = "apellido")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column( name = "contrasenia")
    private String password;

    @Column(name = "token_activacion")
    private String tokenActivation;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private RoleEntity role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private StatusEntity status;
}
