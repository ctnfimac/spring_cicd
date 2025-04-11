package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;
import com.cperalta.jardineria.usuario.domain.ports.output.AuthenticationRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.mapper.BaseUserMapper;
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
