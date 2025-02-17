package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;

public interface RegistrarJardineroRepositoryPort {
    Jardinero registrar(JardineroRecord jardineroRecord);
    Boolean activar(String email, String token);
}
