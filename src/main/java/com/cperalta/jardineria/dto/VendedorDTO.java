package com.cperalta.jardineria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendedorDTO extends  PersonaDTO{
    @NotBlank(message = "EL teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    private String email;

}
