package com.microservice.users.infraestructure.adapters;

import com.microservice.users.domain.ports.output.TokenGeneratorPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGeneratorAdapter implements TokenGeneratorPort {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}