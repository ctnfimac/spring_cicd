package com.cperalta.tienda.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendedorUpdateDTO extends PersonaUpdateDTO{
    private String telefono;
    private String email;

}
