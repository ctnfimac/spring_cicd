package com.microservice.users.domain.ports.output;

import com.microservice.users.domain.models.BaseUser;

import java.util.Optional;

public interface BaseUserRepositoryPort {
    Optional<BaseUser> findByEmail(String email);
}
