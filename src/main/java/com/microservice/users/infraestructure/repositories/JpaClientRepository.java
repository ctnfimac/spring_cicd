package com.microservice.users.infraestructure.repositories;

import com.microservice.users.infraestructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findClientEntityByBaseUserEmailAndBaseUserTokenActivation(String email, String token);
}
