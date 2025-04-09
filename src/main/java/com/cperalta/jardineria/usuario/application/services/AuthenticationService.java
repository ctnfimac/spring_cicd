package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.auth.AuthenticationUseCase;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class AuthenticationService implements AuthenticationUseCase{

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    public Optional<Persona> login(String email, String password) {
        return authenticationUseCase.login(email, password);
    }
}
