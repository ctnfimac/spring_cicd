package com.cperalta.jardineria.usuario.domain.records;

public record ClienteRecord (
    String telefono,
    String nombre,
    String apellido,
    String email,
    String direccion,
    String contrasenia,
    String tokenActivacion
){
}