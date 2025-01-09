package com.cperalta.jardineria.contratacion.infraestructure.dto;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ContrataRequestDTO {
    private Float precio_total;

    @NotBlank(message = "La fecha de reserva es obligatorio")
    @NotNull(message = "Tiene que ingresar una fecha")
    private Date fecha;

    @NotBlank(message = "EL cliente es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún cliente")
    private Cliente cliente;

    @NotBlank(message = "EL jardinero es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún Jardinero")
    private Jardinero jardinero;

    @NotBlank(message = "EL Estado de contratación es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún tipo de Contratación")
    private EstadoContratacion estadoContratacion;
}
