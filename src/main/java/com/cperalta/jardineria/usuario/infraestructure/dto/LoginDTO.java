package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "El email es Obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;
}
