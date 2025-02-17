package com.cperalta.jardineria.usuario.domain.records;

public record JardineroRecord(
        String telefono,
        String nombre,
        String apellido,
        String email,
        String contrasenia,
        String tokenActivacion
) {
}
