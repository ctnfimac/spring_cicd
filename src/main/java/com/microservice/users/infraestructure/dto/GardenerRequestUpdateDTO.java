package com.microservice.users.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GardenerRequestUpdateDTO {
    private Long id;
    private String telephone;
    private String presentation;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private Long roleId;
    private Long statusId;
}

