package com.cperalta.jardineria.usuario.domain.ports.input.auth;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;

import java.util.Optional;

public interface AuthenticationUseCase {
    Optional<BaseUser> login(String email, String password);
}
