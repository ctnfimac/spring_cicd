package com.cperalta.jardineria.servicios.infraestructure.entities;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(
    name = "servicio",
    uniqueConstraints = @UniqueConstraint(
            columnNames = {"tipodeservicio_id", "jardinero_id"}
    )
)
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Opción más común
    @Column(columnDefinition = "UUID")
    private UUID id;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

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
