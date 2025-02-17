package com.cperalta.jardineria.usuario.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Persona {
    private Integer id;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String token_activacion;
    private Rol rol;
    private Estado estado;
}
