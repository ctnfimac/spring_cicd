package com.cperalta.jardineria.usuario.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Role {
    private Long id;
    private String description;
}
