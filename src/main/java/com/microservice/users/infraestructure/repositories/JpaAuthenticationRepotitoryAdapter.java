package com.microservice.users.infraestructure.repositories;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.domain.ports.output.AuthenticationRepositoryPort;
import com.microservice.users.infraestructure.mapper.BaseUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaAuthenticationRepotitoryAdapter implements AuthenticationRepositoryPort {

    private final JpaAuthenticationRepository jpaAuthenticationRepository;
    private final BaseUserMapper baseUserMapper;

    @Override
    public Optional<BaseUser> login(String email, String password) {
        return  jpaAuthenticationRepository.findBaseuserEntityByEmailAndPassword(email, password).map(baseUserMapper::baseUserEntityToBaseUser);

    }
}
