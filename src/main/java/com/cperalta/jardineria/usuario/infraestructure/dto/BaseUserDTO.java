package com.cperalta.jardineria.usuario.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class BaseUserDTO {
    private Long id;

    @NotBlank(message = "El email es obligatorio")
    @Length(max=30, message = "La cantidad máxima de caracteres es 30")
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Length(max = 20, message = "La cantidad máxima de caracteres es 20")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Length(max = 20, message = "La cantidad máxima de caracteres es 20")
    private String lastName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Length(max = 72, message = "La cantidad máxima de caracteres es 72")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    //private Rol rol;
    private Long roleId;

    @NotNull(message = "El estado es obligatorio")
    //private Estado estado;
    private Long statusId;
}
