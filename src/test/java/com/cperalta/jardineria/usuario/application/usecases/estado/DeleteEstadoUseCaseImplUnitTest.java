package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
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
    private EstadoRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private DeleteEstadoUseCaseImpl deleteEstadoUseCase;


    @Test
    @DisplayName("Prueba para eliminar correctamente un estado desde el UseCase")
    public void testDeleteEstadoFound(){
        when(estadoRepositoryPort.delete(1L)).thenReturn(true);

        Boolean respuesta = deleteEstadoUseCase.deleteEstado(1L);
        assertTrue(respuesta);
        verify(estadoRepositoryPort).delete(1L);
    }


    @Test
    @DisplayName("Prueba para eliminar de forma icorrectaun estado desde el UseCase")
    public void testDeleteEstadoNotFound(){
        when(estadoRepositoryPort.delete(1L)).thenReturn(false);

        Boolean respuesta = deleteEstadoUseCase.deleteEstado(2L);
        assertFalse(respuesta);
        verify(estadoRepositoryPort).delete(2L);
    }
}
