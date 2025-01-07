package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.EstadoService;
import com.cperalta.jardineria.usuario.application.usecases.estado.CreateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.DeleteEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.RetrieveEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.application.usecases.estado.UpdateEstadoUseCaseImpl;
import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
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
}
