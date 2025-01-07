package com.cperalta.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VendedorResponseDTO {
    private Integer id;
    private String telefono;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
}
