package com.cperalta.jardineria.usuario.application.usecases.baseuser;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;
import com.cperalta.jardineria.usuario.domain.ports.input.baseuser.BaseUserRetrieveUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.BaseUserRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BaseUserRetrieveUseCaseImpl implements BaseUserRetrieveUseCase {

    private final BaseUserRepositoryPort baseUserRepositoryPort;

    @Override
    public Optional<BaseUser> findByEmail(String email) {
        return baseUserRepositoryPort.findByEmail(email);
    }
}
