package com.cperalta.jardineria.contratacion.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class EstadoContratacionRequestDTO {
    @NotBlank(message = "La descripción del estado no puede estar vacio")
    @Length(min = 4, max = 15, message = "La cantidad mínima de caracteres es 4 y máxima 15")
    String descripcion;
}
