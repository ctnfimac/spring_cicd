package com.cperalta.tienda.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompradorDTO extends PersonaDTO{
    @NotBlank(message = "EL teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "La Dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "EL Email es obligatorio")
    private String email;
}
