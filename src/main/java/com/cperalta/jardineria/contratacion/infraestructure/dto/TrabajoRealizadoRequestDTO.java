package com.cperalta.jardineria.contratacion.infraestructure.dto;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrabajoRealizadoRequestDTO {
    @NotBlank(message = "La foto es obligatoria")
    private String foto;

    private String descripcion;

    @NotBlank(message = "EL jardinero es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún Jardinero")
    private Jardinero jardinero;
}
