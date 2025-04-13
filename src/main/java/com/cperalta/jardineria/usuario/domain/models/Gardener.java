package com.cperalta.jardineria.usuario.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Gardener {
    private Long id;
    private String telephone;
    private String presentation;
    private BaseUser baseUser;
}
