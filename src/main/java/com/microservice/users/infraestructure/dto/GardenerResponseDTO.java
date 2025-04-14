package com.microservice.users.infraestructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GardenerResponseDTO {
    private Long id;
    private String telephone;
    private String presentation;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private String role;
    private String status;
}
