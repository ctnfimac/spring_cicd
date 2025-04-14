package com.microservice.users.contratacion.infraestructure.config;

import com.microservice.users.contratacion.application.services.ContrataService;
import com.microservice.users.contratacion.application.services.EstadoContratacionService;
import com.microservice.users.contratacion.application.services.TrabajoRealizadoService;
import com.microservice.users.contratacion.application.usecases.contrata.CreateContrataUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.contrata.DeleteContrataUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.contrata.RetrieveContrataUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.contrata.UpdateContrataUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.estadocontratacion.CreateEstadoContratacionUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.estadocontratacion.DeleteEstadoContratacionUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.estadocontratacion.RetrieveEstadoContratacionUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.estadocontratacion.UpdateEstadoContratacionUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.trabajorealizado.CreateTrabajoRealizadoUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.trabajorealizado.DeleteTrabajoRealizadoUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.trabajorealizado.RetrieveTrabajoRealizadoUseCaseImpl;
import com.microservice.users.contratacion.application.usecases.trabajorealizado.UpdateTrabajoRealizadoUseCaseImpl;
import com.microservice.users.contratacion.domain.ports.output.ContrataRepositoryPort;
import com.microservice.users.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import com.microservice.users.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigModuleContratacion {

    @Bean
    public EstadoContratacionService estadoContratacionService(EstadoContratacionRepositoryPort estadoContratacionRepositoryPort){
        return new EstadoContratacionService(
                new RetrieveEstadoContratacionUseCaseImpl(estadoContratacionRepositoryPort),
                new CreateEstadoContratacionUseCaseImpl(estadoContratacionRepositoryPort),
                new UpdateEstadoContratacionUseCaseImpl(estadoContratacionRepositoryPort),
                new DeleteEstadoContratacionUseCaseImpl(estadoContratacionRepositoryPort)
        );
    }

    @Bean
    public TrabajoRealizadoService trabajoRealizadoService(TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort){
        return new TrabajoRealizadoService(
                new RetrieveTrabajoRealizadoUseCaseImpl(trabajoRealizadoRepositoryPort),
                new CreateTrabajoRealizadoUseCaseImpl(trabajoRealizadoRepositoryPort),
                new UpdateTrabajoRealizadoUseCaseImpl(trabajoRealizadoRepositoryPort),
                new DeleteTrabajoRealizadoUseCaseImpl(trabajoRealizadoRepositoryPort)
        );
    }

    @Bean
    public ContrataService contrataService(ContrataRepositoryPort contrataRepositoryPort){
        return new ContrataService(
                new RetrieveContrataUseCaseImpl(contrataRepositoryPort),
                new CreateContrataUseCaseImpl(contrataRepositoryPort),
                new UpdateContrataUseCaseImpl(contrataRepositoryPort),
                new DeleteContrataUseCaseImpl(contrataRepositoryPort)
        );
    }

}
