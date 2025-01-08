package com.cperalta.jardineria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCompraDTO {
    @NotBlank(message = "El valor de la descripción es obligatoria")
    @Length(min = 4, max = 15, message = "La cantidad mínima de caracteres es 4 y máxima 15")
    private String descripcion;
}
