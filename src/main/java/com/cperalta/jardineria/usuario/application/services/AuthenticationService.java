package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.auth.AuthenticationUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.persona.PersonaRetrieveUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@AllArgsConstructor
public class AuthenticationService implements AuthenticationUseCase{

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    public Optional<Persona> login(String email, String password) {
        return authenticationUseCase.login(email, password);
    }
}
