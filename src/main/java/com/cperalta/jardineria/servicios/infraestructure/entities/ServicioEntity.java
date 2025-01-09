package com.cperalta.jardineria.servicios.infraestructure.entities;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "com/cperalta/jardineria/servicios")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String descripcion;

    private Long precio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tipodeservicio_id", referencedColumnName = "id")
    private TipoDeServicioEntity tipoDeServicio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jardinero_id", referencedColumnName = "id")
    private JardineroEntity jardinero;
}
