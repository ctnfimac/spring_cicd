package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.output.EstadoRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class CreateEstadoUseCaseImplUnitTest {
    @Mock
    private EstadoRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private CreateEstadoUseCaseImpl createEstadoUseCase;

    @Test
    @DisplayName("Prueba para crear correctamente un Estado desde el UseCase")
    public void testCreateEstadoFound(){
        Estado estado = new Estado(1L, "ACTIVO");

        when(estadoRepositoryPort.create(estado)).thenReturn(estado);

        Estado estadoCreado = createEstadoUseCase.createEstado(estado);
        assertEquals(estadoCreado, estado);
        verify(estadoRepositoryPort).create(estado);
    }
}
