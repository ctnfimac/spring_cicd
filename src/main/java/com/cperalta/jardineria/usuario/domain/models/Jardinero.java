package com.cperalta.jardineria.usuario.domain.models;

import com.cperalta.jardineria.entity.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Jardinero {
    private Integer id;
    private String telefono;
    private String email;
    private String presentacion;
    private Persona persona;
}
