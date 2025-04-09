package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JardineroResponseDTO {
    private Long id;
    private String telefono;
    private String presentacion;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String role;
    private String estado;
}
