package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.*;
import com.cperalta.jardineria.usuario.application.usecases.auth.AuthenticationUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.client.*;
import com.cperalta.jardineria.usuario.application.usecases.status.CreateStatusUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.status.DeleteStatusUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.status.RetrieveStatusUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.status.UpdateStatusUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.gardener.*;
import com.cperalta.jardineria.usuario.application.usecases.baseuser.BaseUserRetrieveUseCaseImpl;
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
