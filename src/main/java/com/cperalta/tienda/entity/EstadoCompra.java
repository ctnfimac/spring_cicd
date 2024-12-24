package com.cperalta.tienda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_compra")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String descripcion;
}
