package com.cperalta.jardineria.usuario.domain.models;

import com.cperalta.jardineria.entity.Estado;
import com.cperalta.jardineria.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Persona {
    private Integer id;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Rol rol;
    private Estado estado;
}
