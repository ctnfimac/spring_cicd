package com.cperalta.jardineria.usuario.domain.ports.input.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;

public interface CreateJardineroUseCase {
    Jardinero create(Jardinero jardinero);
}
