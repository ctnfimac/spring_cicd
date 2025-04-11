package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class JardineroRequestDTO extends BaseUserDTO{
    private Long id;

    @NotBlank(message = "EL teléfono es obligatorio")
    @Length(max=12, message = "La cantidad máxima de caracteres es 12")
    private String telefono;

    @NotBlank(message = "Su presentación es obligatoria")
    private String presentacion;
}
