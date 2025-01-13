package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RetrieveJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.persona.PersonaRetrieveUseCase;
import lombok.AllArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import java.util.List;
import java.util.ArrayList;

//@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService{

    private final PersonaRetrieveUseCase personaRetrieveUseCase;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persona persona = personaRetrieveUseCase.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("Usuario " + email + " not found"));

        Rol rol = persona.getRol();

        System.out.println("ROL ASIGNADO");
        System.out.println(rol.getDescripcion());

        ArrayList<String> roles = new ArrayList<String>();
        roles.add(rol.getDescripcion());

        return User.builder()
                .username(persona.getEmail())
                .password(persona.getContrasenia())
                .roles(rol.getDescripcion())
                .authorities(grantedAuthorities(roles.toArray(new String[0])))//this.grantedAuthorities(rol)
                .accountLocked(false)//usuario.getLocked()
                //.disabled(usuario.getDisabled())
                .disabled(false)
                .build();
    }

    private String[] getAuthorities(String role){
        if("ADMIN".equals(role)){
            return new String[] {"ADMIN"};
        }else if("VENDEDOR".equals(role)){
            return new String[] {"VENDEDOR"};
        } else{
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
