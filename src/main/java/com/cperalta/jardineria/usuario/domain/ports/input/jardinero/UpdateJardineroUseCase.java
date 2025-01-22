package com.cperalta.jardineria.usuario.domain.ports.input.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;

public interface UpdateJardineroUseCase {
    Jardinero update(Long id, Jardinero jardinero);
}
