package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.ports.output.StatusRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class DeleteEstadoUseCaseImplUnitTest {

    @Mock
    private StatusRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private DeleteStatusUseCaseImpl deleteStatusUseCase;


    @Test
    @DisplayName("Prueba para eliminar correctamente un estado desde el UseCase")
    public void testDeleteEstadoFound(){
        when(estadoRepositoryPort.delete(1L)).thenReturn(true);

        Boolean respuesta = deleteStatusUseCase.delete(1L);
        assertTrue(respuesta);
        verify(estadoRepositoryPort).delete(1L);
    }


    @Test
    @DisplayName("Prueba para eliminar de forma icorrectaun estado desde el UseCase")
    public void testDeleteEstadoNotFound(){
        when(estadoRepositoryPort.delete(1L)).thenReturn(false);

        Boolean respuesta = deleteStatusUseCase.delete(2L);
        assertFalse(respuesta);
        verify(estadoRepositoryPort).delete(2L);
    }
}
