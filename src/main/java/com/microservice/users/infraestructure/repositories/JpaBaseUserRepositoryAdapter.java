package com.microservice.users.infraestructure.repositories;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.output.BaseUserRepositoryPort;
import com.microservice.users.infraestructure.mapper.BaseUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaBaseUserRepositoryAdapter implements BaseUserRepositoryPort {

    private final JpaBaseUserRepository jpaBaseUserRepository;
    private final BaseUserMapper baseUserMapper;

    @Override
    public Optional<BaseUser> findByEmail(String email) {
        return jpaBaseUserRepository.findByEmail(email).map(baseUserMapper::baseUserEntityToBaseUser);
    }
}
