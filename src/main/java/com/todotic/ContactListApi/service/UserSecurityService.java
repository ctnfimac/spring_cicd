package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.entity.UserRoleEntity;
import com.todotic.ContactListApi.entity.Usuario;
import com.todotic.ContactListApi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                //.roles(roles)
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(usuario.getLocked())
                .disabled(usuario.getDisabled())
                .build();
    }

    private String[] getAuthorities(String role){
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"random_pedido"};
        }else{
            return new String[] {};
        }
    }


    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);


        for(String role: roles){
            // asigno los roles
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            // agrego a mi lista de authorities sin prefijo ROLE_ sino uno plano
            for(String authority: this.getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
