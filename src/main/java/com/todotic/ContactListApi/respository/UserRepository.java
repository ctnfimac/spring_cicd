package com.todotic.ContactListApi.respository;

import com.todotic.ContactListApi.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Usuario,String> {
    Optional<Usuario> findByUsername(String username);
}
