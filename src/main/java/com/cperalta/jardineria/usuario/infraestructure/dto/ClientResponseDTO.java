package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private String telephone;
    private String address;
    private String latitude;
    private String longitude;
    private String role;
    private String status;
}
