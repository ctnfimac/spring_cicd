package com.cperalta.tienda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String contrasenia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private Rol rol;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    private Estado estado;
}
