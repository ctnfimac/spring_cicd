package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;

import java.util.Optional;

public interface BaseUserRepositoryPort {
    Optional<BaseUser> findByEmail(String email);
}
