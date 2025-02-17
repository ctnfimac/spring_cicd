package com.cperalta.jardineria.usuario.domain.ports.input.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;

public interface RegistrarJardineroUseCase {
   Jardinero registrar(JardineroRecord jardineroRecord);
   Boolean activar(String email, String token);
}
