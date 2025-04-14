package com.microservice.users.infraestructure.repositories;

import com.microservice.users.infraestructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByDescription(String description);
}
