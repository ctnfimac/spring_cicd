package com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroJardineroResponseDTO {
    private String telefono;
    private String nombre;
    private String apellido;
    private String email;
}
