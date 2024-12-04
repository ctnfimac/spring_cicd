package com.cperalta.tienda.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "EL Rol es obligatorio")
    private Integer rolId;

    @NotBlank(message = "EL Estado es obligatorio")
    private Integer estadoId;
}
