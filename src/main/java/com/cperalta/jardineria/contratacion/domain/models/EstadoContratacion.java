package com.cperalta.jardineria.contratacion.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EstadoContratacion {
    private Long id;
    private String descripcion;
}
