package com.cperalta.jardineria.servicios.infraestructure.config;

import com.cperalta.jardineria.servicios.application.services.TipoDeServicioService;
import com.cperalta.jardineria.servicios.application.usecases.RetrieveTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigModuleServicios {

    @Bean
    public TipoDeServicioService tipoDeServicioService(TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort){
        return new TipoDeServicioService(
                new RetrieveTipoDeServicioUseCaseImpl(tipoDeServicioRepositoryPort)
        );
    }

}
