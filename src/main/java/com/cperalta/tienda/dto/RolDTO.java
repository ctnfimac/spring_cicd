package com.cperalta.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDTO {
    @NotBlank(message = "Este campo es obligatorio")
    private String descripcion;
}
