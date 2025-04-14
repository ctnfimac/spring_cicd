package com.microservice.users.domain.ports.input.baseuser;

import com.microservice.users.domain.models.BaseUser;

import java.util.Optional;

public interface BaseUserRetrieveUseCase {
    Optional<BaseUser> findByEmail(String email);
}
