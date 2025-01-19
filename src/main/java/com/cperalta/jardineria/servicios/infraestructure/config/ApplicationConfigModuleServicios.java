package com.cperalta.jardineria.servicios.infraestructure.config;

import com.cperalta.jardineria.servicios.application.services.ServicioService;
import com.cperalta.jardineria.servicios.application.services.TipoDeServicioService;
import com.cperalta.jardineria.servicios.application.usecases.servicio.CreateServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.servicio.DeleteServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.servicio.RetrieveServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.servicio.UpdateServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.tipodeservicio.CreateTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.tipodeservicio.DeleteTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.tipodeservicio.RetrieveTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.application.usecases.tipodeservicio.UpdateTipoDeServicioUseCaseImpl;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import com.cperalta.jardineria.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigModuleServicios {

    @Bean
    public TipoDeServicioService tipoDeServicioService(TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort){
        return new TipoDeServicioService(
                new RetrieveTipoDeServicioUseCaseImpl(tipoDeServicioRepositoryPort),
                new CreateTipoDeServicioUseCaseImpl(tipoDeServicioRepositoryPort),
                new UpdateTipoDeServicioUseCaseImpl(tipoDeServicioRepositoryPort),
                new DeleteTipoDeServicioUseCaseImpl(tipoDeServicioRepositoryPort)
        );
    }

    @Bean
    public ServicioService servicioService(ServicioRepositoryPort servicioRepositoryPort){
        return new ServicioService(
                new RetrieveServicioUseCaseImpl(servicioRepositoryPort),
                new CreateServicioUseCaseImpl(servicioRepositoryPort),
                new UpdateServicioUseCaseImpl(servicioRepositoryPort),
                new DeleteServicioUseCaseImpl(servicioRepositoryPort)
        );
    }

}
