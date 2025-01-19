package com.cperalta.jardineria.servicios.infraestructure.dto;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServicioUpdateRequestDTO {
    private String descripcion;
    private Float precio;
    private TipoDeServicio tipoDeServicio;
    private Jardinero jardinero;
}
