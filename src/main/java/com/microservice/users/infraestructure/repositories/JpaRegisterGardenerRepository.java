package com.microservice.users.infraestructure.repositories;

import com.microservice.users.infraestructure.entities.GardenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRegisterGardenerRepository extends JpaRepository<GardenerEntity, Long> {
}
