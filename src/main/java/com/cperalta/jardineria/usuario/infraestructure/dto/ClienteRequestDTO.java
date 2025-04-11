package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class ClienteRequestDTO extends BaseUserDTO{
    @NotBlank(message = "EL teléfono es obligatorio")
    @Length(max=12, message = "La cantidad máxima de caracteres es 12")
    private String telefono;

    @NotBlank(message = "La Dirección es obligatoria")
    @Length(max=50, message = "La cantidad máxima de caracteres es 50")
    private String direccion;

    private String latitud;
    private String longitud;
}
