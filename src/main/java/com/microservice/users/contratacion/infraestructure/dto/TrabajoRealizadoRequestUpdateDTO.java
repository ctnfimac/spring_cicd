package com.microservice.users.contratacion.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrabajoRealizadoRequestUpdateDTO {
    private Long id;
    private String foto;
    private String descripcion;
    private Long jardineroId;
}
