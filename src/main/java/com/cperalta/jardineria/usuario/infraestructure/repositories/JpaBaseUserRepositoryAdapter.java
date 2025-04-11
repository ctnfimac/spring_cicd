package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;
import com.cperalta.jardineria.usuario.domain.ports.output.BaseUserRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.mapper.BaseUserMapper;
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
