package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Setter
@Getter
public class RolDTO {

    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    @Length(min = 4, message = "La cantidad mínima de caracteres es 4")
    private String descripcion;
}
