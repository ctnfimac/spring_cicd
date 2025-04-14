package com.microservice.users.servicios.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TipoDeServicio {
    private Long id;
    private String nombre;
    private String foto;
}
