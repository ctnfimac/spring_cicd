package com.cperalta.jardineria.usuario.infraestructure.entities;

import com.cperalta.jardineria.entity.Rol;
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
    private String contrasenia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private RolEntity rol;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private EstadoEntity estado;
}
