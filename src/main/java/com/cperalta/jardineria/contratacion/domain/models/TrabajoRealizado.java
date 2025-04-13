package com.cperalta.jardineria.contratacion.domain.models;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
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
