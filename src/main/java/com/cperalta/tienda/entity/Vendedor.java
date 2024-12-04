package com.cperalta.tienda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendedor")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String telefono;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;
}
