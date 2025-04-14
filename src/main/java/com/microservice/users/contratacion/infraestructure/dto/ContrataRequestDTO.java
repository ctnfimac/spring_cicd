package com.microservice.users.contratacion.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ContrataRequestDTO {

    private Long id;

    private Float precio_total;

    @NotBlank(message = "La fecha de reserva es obligatorio")
    @NotNull(message = "Tiene que ingresar una fecha")
    private Date fecha;

    @NotNull(message = "Tiene que seleccionar algún cliente")
    private Long clienteId;

    @NotNull(message = "Tiene que seleccionar algún Jardinero")
    private Long jardineroId;

    @NotNull(message = "Tiene que seleccionar algún tipo de Contratación")
    private Long estadoContratacionId;
}
