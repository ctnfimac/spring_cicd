package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.*;
import com.cperalta.jardineria.usuario.application.usecases.auth.AuthenticationUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.cliente.CreateClienteUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.cliente.DeleteClienteUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.cliente.RetrieveClienteUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.cliente.UpdateClienteUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.CreateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.DeleteEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.RetrieveEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.UpdateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.jardinero.*;
import com.cperalta.jardineria.usuario.application.usecases.persona.PersonaRetrieveUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.CreateRolUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.DeleteRolUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.RetrieveRolUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.UpdateRolUseCaseImpl;
import com.cperalta.jardineria.usuario.domain.ports.output.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public RolService rolService(RolRepositoryPort rolRepositoryPort){
        return new RolService(
                new RetrieveRolUseCaseImpl(rolRepositoryPort),
                new CreateRolUseCaseImpl(rolRepositoryPort),
                new DeleteRolUseCaseImpl(rolRepositoryPort),
                new UpdateRolUseCaseImpl(rolRepositoryPort)
        );
    }

    @Bean
    public JardineroService jardineroService(JardineroRepositoryPort jardineroRepositoryPort){
        return new JardineroService(
                new RetrieveJardineroUseCaseImpl(jardineroRepositoryPort),
                new CreateJardineroUseCaseImpl(jardineroRepositoryPort),
                new UpdateJardineroUseCaseImpl(jardineroRepositoryPort),
                new DeleteJardineroUseCaseImpl(jardineroRepositoryPort)
        );
    }

    @Bean
    public ClienteService clienteService(ClienteRepositoryPort clienteRepositoryPort){
        return new ClienteService(
                new RetrieveClienteUseCaseImpl(clienteRepositoryPort),
                new CreateClienteUseCaseImpl(clienteRepositoryPort),
                new UpdateClienteUseCaseImpl(clienteRepositoryPort),
                new DeleteClienteUseCaseImpl(clienteRepositoryPort)
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

    @Bean
    public RegistrarJardineroService registrarJardineroService(RegistrarJardineroRepositoryPort registrarJardineroRepositoryPort,
                                                               PasswordEncoderPort passwordEncoderPort, EmailSenderPort emailSenderPort,
                                                               TokenGeneratorPort tokenGeneratorPort){
        return new RegistrarJardineroService(
                new RegistrarJardineroUseCaseImpl(registrarJardineroRepositoryPort,passwordEncoderPort, emailSenderPort, tokenGeneratorPort)
        );
    }
}
