package com.microservice.users.domain.ports.output;

import com.microservice.users.domain.models.BaseUser;

import java.util.Optional;

public interface AuthenticationRepositoryPort {
    Optional<BaseUser> login(String email, String password);
}
