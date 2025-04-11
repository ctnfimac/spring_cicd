package com.cperalta.jardineria.usuario.domain.ports.input.baseuser;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;

import java.util.Optional;

public interface BaseUserRetrieveUseCase {
    Optional<BaseUser> findByEmail(String email);
}
