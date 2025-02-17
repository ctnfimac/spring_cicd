package com.cperalta.jardineria.usuario.infraestructure.adapters;

import com.cperalta.jardineria.usuario.domain.ports.output.TokenGeneratorPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGeneratorAdapter implements TokenGeneratorPort {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}