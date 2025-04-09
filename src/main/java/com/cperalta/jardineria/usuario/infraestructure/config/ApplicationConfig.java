package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.*;
import com.cperalta.jardineria.usuario.application.usecases.auth.AuthenticationUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.cliente.*;
import com.cperalta.jardineria.usuario.application.usecases.estado.CreateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.DeleteEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.RetrieveEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.UpdateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.jardinero.*;
import com.cperalta.jardineria.usuario.application.usecases.persona.PersonaRetrieveUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.CreateRoleUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.DeleteRoleUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.RetrieveRoleUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.rol.UpdateRoleUseCaseImpl;
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
    public RoleService rolService(RoleRepositoryPort roleRepositoryPort){
        return new RoleService(
                new RetrieveRoleUseCaseImpl(roleRepositoryPort),
                new CreateRoleUseCaseImpl(roleRepositoryPort),
                new DeleteRoleUseCaseImpl(roleRepositoryPort),
                new UpdateRoleUseCaseImpl(roleRepositoryPort)
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
                                                               TokenGeneratorPort tokenGeneratorPort, EncryptionPort encryptionPort){
        return new RegistrarJardineroService(
                new RegistrarJardineroUseCaseImpl(registrarJardineroRepositoryPort,passwordEncoderPort, emailSenderPort, tokenGeneratorPort, encryptionPort)
        );
    }

    @Bean
    public RegistrarClienteService registrarClienteService(RegistrarClienteRepositoryPort registrarClienteRepositoryPort,
                                                               PasswordEncoderPort passwordEncoderPort, EmailSenderPort emailSenderPort,
                                                               TokenGeneratorPort tokenGeneratorPort, EncryptionPort encryptionPort){
        return new RegistrarClienteService(
                new RegistrarClienteUseCaseImpl(registrarClienteRepositoryPort,passwordEncoderPort, emailSenderPort, tokenGeneratorPort, encryptionPort)
        );
    }
}
