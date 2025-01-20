package com.cperalta.jardineria.servicios.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDeServicioUpdateRequestDTO {
    private Long id;
    private String nombre;
    private String foto;
}
