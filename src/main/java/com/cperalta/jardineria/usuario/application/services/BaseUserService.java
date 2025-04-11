package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;
import com.cperalta.jardineria.usuario.domain.ports.input.baseuser.BaseUserRetrieveUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BaseUserService implements BaseUserRetrieveUseCase {
    private final BaseUserRetrieveUseCase baseUserRetrieveUseCase;

    @Override
    public Optional<BaseUser> findByEmail(String email) {
        return baseUserRetrieveUseCase.findByEmail(email);
    }
}
