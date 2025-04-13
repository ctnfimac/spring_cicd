package com.cperalta.jardineria.servicios.infraestructure.dto;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.usuario.domain.models.Gardener;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ServicioResponseDTO {
    private UUID id;
    private String descripcion;
    private Long precio;
    private TipoDeServicio tipoDeServicio;
    private Gardener gardener;
}
