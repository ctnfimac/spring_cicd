package com.cperalta.tienda.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class EstadoDTO {
    @NotBlank(message = "El valor de la descripción es obligatoria")
    @Length(min = 4, max = 15, message = "La cantidad mínima de caracteres es 4 y máxima 15")
    private String descripcion;
}
