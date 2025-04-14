package com.microservice.users.infraestructure.dto.registerclient;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterClientRequestDTO {
    @NotBlank(message = "EL teléfono es obligatorio")
    @Length(max=12, message = "La cantidad máxima de caracteres es 12")
    private String telephone;

    @NotBlank(message = "El nombre es obligatorio")
    @Length(max = 20, message = "La cantidad máxima de caracteres es 20")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Length(max = 20, message = "La cantidad máxima de caracteres es 20")
    private String lastName;

    @NotBlank(message = "El email es obligatorio")
    @Length(max=30, message = "La cantidad máxima de caracteres es 30")
    private String email;

    @NotBlank(message = "La dirección es obligatorio")
    @Length(max=50, message = "La cantidad máxima de caracteres es 50")
    private String address;

    @NotBlank(message = "La contraseña es obligatoria")
    @Length(max = 72, message = "La cantidad máxima de caracteres es 72")
    private String password;

}
