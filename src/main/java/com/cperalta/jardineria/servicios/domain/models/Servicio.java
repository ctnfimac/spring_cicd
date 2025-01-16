package com.cperalta.jardineria.servicios.domain.models;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Servicio {
    private Long id;
    private String descripcion;
    private Float precio;
    private TipoDeServicio tipoDeServicio;
    private Jardinero jardinero;
}
