package com.cperalta.jardineria.contratacion.infraestructure.entities;

import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "contrata")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContrataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float precio_total;

    private Date fecha;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity cliente;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jardinero_id", referencedColumnName = "id")
    private JardineroEntity jardinero;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estadocontratacion_id", referencedColumnName = "id")
    private EstadoContratacionEntity estadoContratacion;
}
