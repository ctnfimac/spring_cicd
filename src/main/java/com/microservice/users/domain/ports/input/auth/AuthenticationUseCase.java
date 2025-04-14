package com.microservice.users.domain.ports.input.auth;

import com.microservice.users.domain.models.BaseUser;

import java.util.Optional;

public interface AuthenticationUseCase {
    Optional<BaseUser> login(String email, String password);
}
