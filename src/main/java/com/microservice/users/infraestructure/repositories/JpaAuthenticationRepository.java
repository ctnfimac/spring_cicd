package com.microservice.users.infraestructure.repositories;

import com.microservice.users.infraestructure.entities.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAuthenticationRepository extends JpaRepository<BaseUserEntity, Long> {
    Optional<BaseUserEntity> findBaseuserEntityByEmailAndPassword(String email, String password);
}
