package com.cperalta.jardineria.servicios.infraestructure.dto;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ServicioUpdateRequestDTO {
    private UUID id;
    private String descripcion;
    private Float precio;
    private TipoDeServicio tipoDeServicio;
    private Jardinero jardinero;
}
