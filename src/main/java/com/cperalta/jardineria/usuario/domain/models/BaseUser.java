package com.cperalta.jardineria.usuario.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BaseUser {
    private Integer id;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private String tokenActivation;
    private Role role;
    private Status status;
}
