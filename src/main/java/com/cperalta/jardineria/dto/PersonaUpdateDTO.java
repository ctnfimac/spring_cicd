package com.cperalta.tienda.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaUpdateDTO {
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Integer rolId;
    private Integer estadoId;
}