package com.microservice.users.infraestructure.config;

import com.microservice.users.application.services.*;
import com.microservice.users.application.usecases.client.*;
import com.microservice.users.application.usecases.gardener.*;
import com.microservice.users.domain.ports.output.*;
import com.microservice.users.application.usecases.auth.AuthenticationUseCaseImpl;
import com.microservice.users.application.usecases.status.CreateStatusUseCaseImpl;
import com.microservice.users.application.usecases.status.DeleteStatusUseCaseImpl;
import com.microservice.users.application.usecases.status.RetrieveStatusUseCaseImpl;
import com.microservice.users.application.usecases.status.UpdateStatusUseCaseImpl;
import com.microservice.users.application.usecases.baseuser.BaseUserRetrieveUseCaseImpl;
import com.microservice.users.application.usecases.rol.CreateRoleUseCaseImpl;
import com.microservice.users.application.usecases.rol.DeleteRoleUseCaseImpl;
import com.microservice.users.application.usecases.rol.RetrieveRoleUseCaseImpl;
import com.microservice.users.application.usecases.rol.UpdateRoleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public StatusService estatusService(StatusRepositoryPort statusRepositoryPort){
        return new StatusService(
                new RetrieveStatusUseCaseImpl(statusRepositoryPort),
                new CreateStatusUseCaseImpl(statusRepositoryPort),
                new DeleteStatusUseCaseImpl(statusRepositoryPort),
                new UpdateStatusUseCaseImpl(statusRepositoryPort)
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
    public GardenerService jardineroService(GardenerRepositoryPort gardenerRepositoryPort){
        return new GardenerService(
                new RetrieveGardenerUseCaseImpl(gardenerRepositoryPort),
                new CreateGardenerUseCaseImpl(gardenerRepositoryPort),
                new UpdateGardenerUseCaseImpl(gardenerRepositoryPort),
                new DeleteGardenerUseCaseImpl(gardenerRepositoryPort)
        );
    }

    @Bean
    public ClientService clienteService(ClientRepositoryPort clientRepositoryPort){
        return new ClientService(
                new RetrieveClientUseCaseImpl(clientRepositoryPort),
                new CreateClientUseCaseImpl(clientRepositoryPort),
                new UpdateClientUseCaseImpl(clientRepositoryPort),
                new DeleteClientUseCaseImpl(clientRepositoryPort)
        );
    }

    @Bean
    public AuthenticationService authenticationService(AuthenticationRepositoryPort authenticationRepositoryPort){
        return new AuthenticationService(
                new AuthenticationUseCaseImpl(authenticationRepositoryPort)
        );
    }

    @Bean
    public UserSecurityService userSecurityService(BaseUserRepositoryPort baseUserRepositoryPort){
        return new UserSecurityService(
                new BaseUserRetrieveUseCaseImpl(baseUserRepositoryPort)
        );
    }

    @Bean
    public BaseUserService baseUserService(BaseUserRepositoryPort baseUserRepositoryPort){
        return new BaseUserService(
                new BaseUserRetrieveUseCaseImpl(baseUserRepositoryPort)
        );
    }

    @Bean
    public RegisterGardenerService registrarJardineroService(RegisterGardenerRepositoryPort registerGardenerRepositoryPort,
                                                             PasswordEncoderPort passwordEncoderPort, EmailSenderPort emailSenderPort,
                                                             TokenGeneratorPort tokenGeneratorPort, EncryptionPort encryptionPort){
        return new RegisterGardenerService(
                new RegisterGardenerUseCaseImpl(registerGardenerRepositoryPort,passwordEncoderPort, emailSenderPort, tokenGeneratorPort, encryptionPort)
        );
    }

    @Bean
    public RegisterClientService registrarClienteService(RegisterClientRepositoryPort registerClientRepositoryPort,
                                                         PasswordEncoderPort passwordEncoderPort, EmailSenderPort emailSenderPort,
                                                         TokenGeneratorPort tokenGeneratorPort, EncryptionPort encryptionPort){
        return new RegisterClientService(
                new RegisterClientUseCaseImpl(registerClientRepositoryPort,passwordEncoderPort, emailSenderPort, tokenGeneratorPort, encryptionPort)
        );
    }
}
