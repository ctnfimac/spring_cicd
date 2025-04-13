package com.cperalta.jardineria.contratacion.domain.models;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.models.Gardener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Contrata {
    private Long id;
    private Float precio_total;
    private Date fecha;
    private Client client;
    private Gardener gardener;
    private EstadoContratacion estadoContratacion;
}
