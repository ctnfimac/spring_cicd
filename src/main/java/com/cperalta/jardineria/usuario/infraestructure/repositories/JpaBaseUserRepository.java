package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBaseUserRepository extends JpaRepository<BaseUserEntity, Long> {
    Optional<BaseUserEntity> findByEmail(String email);
}
