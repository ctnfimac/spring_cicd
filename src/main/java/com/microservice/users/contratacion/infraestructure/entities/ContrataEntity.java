package com.microservice.users.contratacion.infraestructure.entities;

import com.microservice.users.infraestructure.entities.ClientEntity;
import com.microservice.users.infraestructure.entities.GardenerEntity;
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
    private ClientEntity client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jardinero_id", referencedColumnName = "id")
    private GardenerEntity gardener;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estadocontratacion_id", referencedColumnName = "id")
    private EstadoContratacionEntity estadoContratacion;
}
