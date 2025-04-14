package com.microservice.users.infraestructure.dto.registerclient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterClientResponseDTO {
    private String telephone;
    private String name;
    private String lastName;
    private String email;
    private String address;
}
