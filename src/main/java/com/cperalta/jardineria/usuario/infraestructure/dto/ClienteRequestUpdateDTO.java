package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestUpdateDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private String latitud;
    private String longitud;
    private Long rolId;
    private Long estadoId;
}
