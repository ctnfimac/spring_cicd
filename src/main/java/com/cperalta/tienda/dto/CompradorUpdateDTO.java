package com.cperalta.tienda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompradorUpdateDTO extends PersonaUpdateDTO{
    private String telefono;
    private String direccion;
    private String email;
}
