package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JardineroRequestUpdateDTO {
    private Long id;
    private String telefono;
    private String presentacion;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Long rolId;
    private Long estadoId;
}

