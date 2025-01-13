package com.cperalta.jardineria.usuario.application.usecases.auth;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.auth.AuthenticationUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.AuthenticationRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthenticationRepositoryPort authenticationRepositoryPort;

    @Override
    public Optional<Persona> login(String email, String password) {
        return authenticationRepositoryPort.login(email, password);
    }
}
