package com.cperalta.jardineria.usuario.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String telefono;
    private String direccion;
    private BaseUser baseUser;
    private String latitud;
    private String longitud;
}
