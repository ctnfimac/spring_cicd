package com.cperalta.tienda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comprador")
@Setter
@Getter
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String telefono;
    private String direccion;
    private String email;
    private String latitud;
    private String longitud;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;
}
