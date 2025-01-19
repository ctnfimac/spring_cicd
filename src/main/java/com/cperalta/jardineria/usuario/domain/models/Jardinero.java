package com.cperalta.jardineria.usuario.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Jardinero {
    private Long id;
    private String telefono;
    private String presentacion;
    private Persona persona;
}
