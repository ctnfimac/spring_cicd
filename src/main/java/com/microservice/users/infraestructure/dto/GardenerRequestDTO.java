package com.microservice.users.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class GardenerRequestDTO extends BaseUserDTO{
    private Long id;

    @NotBlank(message = "EL teléfono es obligatorio")
    @Length(max=12, message = "La cantidad máxima de caracteres es 12")
    private String telephone;

    @NotBlank(message = "Su presentación es obligatoria")
    private String presentation;
}
