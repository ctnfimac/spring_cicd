package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.*;
import com.cperalta.jardineria.usuario.application.usecases.auth.AuthenticationUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.CreateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.DeleteEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.RetrieveEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.UpdateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.jardinero.RetrieveJardineroUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.persona.PersonaRetrieveUseCaseImpl;
import com.cperalta.jardineria.usuario.domain.ports.output.AuthenticationRepositoryPort;
import com.cperalta.jardineria.usuario.domain.ports.output.EstadoRepositoryPort;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import com.cperalta.jardineria.usuario.domain.ports.output.PersonaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {
    @Bean
    public EstadoService estadoService(EstadoRepositoryPort estadoRepositoryPort){
        return new EstadoService(
                new RetrieveEstadoUseCaseImpl(estadoRepositoryPort),
                new CreateEstadoUseCaseImpl(estadoRepositoryPort),
                new DeleteEstadoUseCaseImpl(estadoRepositoryPort),
                new UpdateEstadoUseCaseImpl(estadoRepositoryPort)
        );
    }

    @Bean
    public JardineroService jardineroService(JardineroRepositoryPort jardineroRepositoryPort){
        return new JardineroService(
                new RetrieveJardineroUseCaseImpl(jardineroRepositoryPort)
        );
    }

    @Bean
    public AuthenticationService authenticationService(AuthenticationRepositoryPort authenticationRepositoryPort){
        return new AuthenticationService(
                new AuthenticationUseCaseImpl(authenticationRepositoryPort)
        );
    }

    @Bean
    public UserSecurityService userSecurityService(PersonaRepositoryPort personaRepositoryPort){
        return new UserSecurityService(
                new PersonaRetrieveUseCaseImpl(personaRepositoryPort)
        );
    }

    @Bean
    public PersonaService personaService(PersonaRepositoryPort personaRepositoryPort){
        return new PersonaService(
                new PersonaRetrieveUseCaseImpl(personaRepositoryPort)
        );
    }
}
