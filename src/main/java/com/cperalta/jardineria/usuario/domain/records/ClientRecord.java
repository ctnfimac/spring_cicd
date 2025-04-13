package com.cperalta.jardineria.usuario.domain.records;

public record ClientRecord (
    String telephone,
    String name,
    String lastName,
    String email,
    String address,
    String password,
    String tokenActivation
){
}