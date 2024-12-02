package com.cperalta.tienda.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoDTO {
    @NotBlank(message = "El valor de la descripción es obligatoria")
    private String descripcion;
}
