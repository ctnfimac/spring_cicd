package com.microservice.users.domain.ports.input.role;

import com.microservice.users.domain.models.Role;

import java.util.Optional;

public interface UpdateRoleUseCase {
    Optional<Role> update(Long id, Role role);
}
