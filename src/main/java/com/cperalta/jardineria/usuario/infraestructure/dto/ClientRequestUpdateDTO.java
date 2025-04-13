package com.cperalta.jardineria.usuario.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestUpdateDTO {
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private String telephone;
    private String address;
    private String latitude;
    private String longitude;
    private Long roleId;
    private Long statusId;
}
