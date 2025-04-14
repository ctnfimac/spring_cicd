package com.microservice.users.application.usecases.baseuser;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.input.baseuser.BaseUserRetrieveUseCase;
import com.microservice.users.domain.ports.output.BaseUserRepositoryPort;
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
