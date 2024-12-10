package com.cperalta.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaDTO{
    @NotBlank(message = "EL Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "EL Apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La Contraseña es obligatorio")
    private String contrasenia;

    @NotNull(message = "EL Rol es obligatorio")
    private Integer rolId;

    @NotNull(message = "EL Estado es obligatorio")
    private Integer estadoId;
}
