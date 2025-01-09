package com.cperalta.jardineria.contratacion.domain.models;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
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
    private Cliente cliente;
    private Jardinero jardinero;
    private EstadoContratacion estadoContratacion;
}
