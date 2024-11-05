package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.entity.UserRoleEntity;
import com.todotic.ContactListApi.entity.Usuario;
import com.todotic.ContactListApi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Usuario " + username + " not found"));

        String[] roles = usuario.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(roles)
                .accountLocked(usuario.getLocked())
                .disabled(usuario.getDisabled())
                .build();
    }
}
