package com.cperalta.jardineria.servicios.domain.models;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Servicio {
    private UUID id;
    private String descripcion;
    private Float precio;
    private TipoDeServicio tipoDeServicio;
    private Gardener gardener;
}
