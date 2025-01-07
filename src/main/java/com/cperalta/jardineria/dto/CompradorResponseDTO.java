package com.cperalta.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CompradorResponseDTO{
    private Integer id;
    private String nombre;
    private String apellido;
    private String rol;
    private String estado;

    private String telefono;
    private String direccion;
    private String email;
    private String latitud;
    private String longitud;
}
