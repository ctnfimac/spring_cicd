package com.cperalta.tienda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comprador")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String telefono;
    private String direccion;

    @Column(unique = true)
    private String email;
    private String latitud;
    private String longitud;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;
}
