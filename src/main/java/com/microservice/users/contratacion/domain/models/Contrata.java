package com.microservice.users.contratacion.domain.models;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.models.Gardener;
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
