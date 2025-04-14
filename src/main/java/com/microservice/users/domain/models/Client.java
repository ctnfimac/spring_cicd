package com.microservice.users.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Client {
    private Long id;
    private String telephone;
    private String address;
    private BaseUser baseUser;
    private String latitude;
    private String longitude;
}
