package com.cperalta.jardineria.servicios.infraestructure.config;

import com.cperalta.jardineria.servicios.application.services.ServicioService;
import com.cperalta.jardineria.servicios.application.services.TipoDeServicioService;
import com.cperalta.jardineria.servicios.application.usecases.servicio.RetrieveServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.tipodeservicio.RetrieveTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.domain.ports.input.servicio.RetrieveServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
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

    @Bean
    public ServicioService servicioService(ServicioRepositoryPort servicioRepositoryPort){
        return new ServicioService(
                new RetrieveServicioUseCaseImpl(servicioRepositoryPort)
        );
    }

}
