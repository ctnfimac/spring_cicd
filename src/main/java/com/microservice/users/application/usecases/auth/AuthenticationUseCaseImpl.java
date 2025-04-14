package com.microservice.users.application.usecases.auth;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.input.auth.AuthenticationUseCase;
import com.microservice.users.domain.ports.output.AuthenticationRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthenticationRepositoryPort authenticationRepositoryPort;

    @Override
    public Optional<BaseUser> login(String email, String password) {
        return authenticationRepositoryPort.login(email, password);
    }
}
