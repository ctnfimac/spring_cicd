package com.microservice.users.domain.ports.input.role;

import com.microservice.users.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface RetrieveRoleUseCase {
    Optional<Role> getById(Long id);
    List<Role> getAll();
}
