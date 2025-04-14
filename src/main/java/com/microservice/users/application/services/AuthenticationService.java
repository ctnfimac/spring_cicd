package com.microservice.users.application.services;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.input.auth.AuthenticationUseCase;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class AuthenticationService implements AuthenticationUseCase{

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    public Optional<BaseUser> login(String email, String password) {
        return authenticationUseCase.login(email, password);
    }
}
