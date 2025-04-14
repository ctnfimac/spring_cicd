package com.microservice.users.servicios.infraestructure.dto;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.domain.models.Gardener;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ServicioUpdateRequestDTO {
    private UUID id;
    private String descripcion;
    private Float precio;
    private TipoDeServicio tipoDeServicio;
    private Gardener gardener;
}
