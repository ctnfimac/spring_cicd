package com.cperalta.jardineria.usuario.application.usecases.status;


import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class UpdateEstadoUseCaseImplUnitTest {
    @Mock
    private StatusRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private UpdateStatusUseCaseImpl updateStatusUseCase;

    @Test
    @DisplayName("Prueba para Actualizar correctamente un estado")
    public void testUpdateEstadoFound(){
        Status estado = new Status(1L, "ACTIVO");
        when(estadoRepositoryPort.update(1L, estado )).thenReturn(Optional.of(estado));

        Optional<Status> estadoActualizado = updateStatusUseCase.update(1L, estado);
        assertEquals(estadoActualizado.get(), estado);
        verify(estadoRepositoryPort).update(1L, estado);
    }

    @Test
    @DisplayName("Prueba para Actualizar un estado de forma incorrecta")
    public void testUpdateEstadoNotFound(){
        Status estado = new Status(1L, "ACTIVO");
        when(estadoRepositoryPort.update(10L, estado )).thenReturn(Optional.empty());

        Optional<Status> estadoActualizado = updateStatusUseCase.update(10L, estado);
        assertEquals(estadoActualizado, Optional.empty());
        verify(estadoRepositoryPort).update(10L, estado);
    }
}
