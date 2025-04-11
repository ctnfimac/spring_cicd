package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteResponseDTO {
    private Integer id;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private String latitud;
    private String longitud;
    private String role;
    private String status;
}
