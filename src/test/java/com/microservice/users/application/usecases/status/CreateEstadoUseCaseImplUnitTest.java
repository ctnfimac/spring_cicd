package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.models.Status;
import com.microservice.users.domain.ports.output.StatusRepositoryPort;
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
    private StatusRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private CreateStatusUseCaseImpl createStatusUseCase;

    @Test
    @DisplayName("Prueba para crear correctamente un Status desde el UseCase")
    public void testCreateEstadoFound(){
        Status status = new Status(1L, "ACTIVO");

        when(estadoRepositoryPort.create(status)).thenReturn(status);

        Status estadoCreado = createStatusUseCase.create(status);
        assertEquals(estadoCreado, status);
        verify(estadoRepositoryPort).create(status);
    }
}
