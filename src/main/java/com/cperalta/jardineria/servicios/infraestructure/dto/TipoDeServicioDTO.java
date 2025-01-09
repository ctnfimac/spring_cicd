package com.cperalta.jardineria.servicios.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class TipoDeServicioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Length(max = 30, message = "La cantidad máxima de caracteres es 30")
    @NotNull
    private String nombre;

    @NotBlank(message = "La foto del servicio es obligatorio")
    @Length(max = 50, message = "La cantidad máxima de caracteres es 50")
    private String foto;
}
