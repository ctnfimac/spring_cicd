package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class ClienteRequestDTO extends PersonaDTO{
    @NotBlank(message = "EL teléfono es obligatorio")
    @Length(max=12, message = "La cantidad máxima de caracteres es 12")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Length(max=30, message = "La cantidad máxima de caracteres es 30")
    private String email;

}
