package com.cperalta.jardineria.usuario.infraestructure.dto.registrocliente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroClienteResponseDTO {
    private String telefono;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
}
