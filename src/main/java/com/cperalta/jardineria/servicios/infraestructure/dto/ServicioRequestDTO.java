package com.cperalta.jardineria.servicios.infraestructure.dto;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class ServicioRequestDTO {
    @Length(max=100, message = "La cantidad máxima de caracteres es 100")
    private String descripcion;

    @NotBlank(message = "EL precio es obligatorio")
    @NotNull(message = "EL precio tiene que tener algun valor")
    private Long precio;

    @NotBlank(message = "EL Tipo de Servicio es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún Tipo de servicio")
    private TipoDeServicio tipoDeServicio;

    @NotBlank(message = "EL Jardinero es obligatorio")
    @NotNull(message = "Tiene que seleccionar algún Jardinero")
    private Jardinero jardinero;
}
