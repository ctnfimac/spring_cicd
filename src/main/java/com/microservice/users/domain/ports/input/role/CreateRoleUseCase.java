package com.microservice.users.domain.ports.input.role;

import com.microservice.users.domain.models.Role;

public interface CreateRoleUseCase {
    Role create(Role role);
}
