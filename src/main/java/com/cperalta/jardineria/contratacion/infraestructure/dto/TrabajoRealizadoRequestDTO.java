package com.cperalta.jardineria.contratacion.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrabajoRealizadoRequestDTO {

    private Long id;

    @NotBlank(message = "La foto es obligatoria")
    private String foto;

    private String descripcion;

    //@NotBlank(message = "EL jardinero es obligatorio")
    @NotNull(message = "Tiene que ingresar algún Jardinero")
    private Long jardineroId;
    //private Jardinero jardinero;
}
