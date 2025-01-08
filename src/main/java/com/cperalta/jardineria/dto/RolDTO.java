package com.cperalta.jardineria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDTO {
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}
