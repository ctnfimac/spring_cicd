package com.microservice.users.application.services;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.input.baseuser.BaseUserRetrieveUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BaseUserService implements BaseUserRetrieveUseCase {
    private final BaseUserRetrieveUseCase baseUserRetrieveUseCase;

    @Override
    public Optional<BaseUser> findByEmail(String email) {
        return baseUserRetrieveUseCase.findByEmail(email);
    }
}
