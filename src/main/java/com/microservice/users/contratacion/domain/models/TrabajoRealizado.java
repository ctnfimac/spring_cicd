package com.microservice.users.contratacion.domain.models;

import com.microservice.users.domain.models.Gardener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TrabajoRealizado {
    private Long id;
    private String foto;
    private String descripcion;
    private Gardener gardener;
}
