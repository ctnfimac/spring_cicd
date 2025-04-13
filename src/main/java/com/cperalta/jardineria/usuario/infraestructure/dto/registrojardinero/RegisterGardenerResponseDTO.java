package com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterGardenerResponseDTO {
    private String telephone;
    private String name;
    private String lastName;
    private String email;
}
