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
public class GardenerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefono", unique = true)
    private String telephone;

    @Column(name = "presentacion")
    private String presentation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private BaseUserEntity baseUser;
}
