package com.cperalta.jardineria.usuario.application.usecases.status;

import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class RetrieveEstadoUseCaseImplUnitTest {
    @Mock
    private StatusRepositoryPort statusRepositoryPort;

    @InjectMocks
    private RetrieveStatusUseCaseImpl retrieveStatusUseCase;

    @Test
    @DisplayName("Prueba de obtencion de todos los estados desde el UseCase")
    void testGetAllEstadosFound() {
        // Configuracion del mock para devolver una lista de estados
        List<Status> estados = Arrays.asList(
                new Status(1L, "BLOQUEADO"),
                new Status(2L, "ACTIVO")
        );
        when(statusRepositoryPort.findAll()).thenReturn(estados);

        //List<Status> result = statusRepositoryPort.findAll();
        List<Status> result = retrieveStatusUseCase.getAll();

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(2, result.size());
        assertEquals("BLOQUEADO", result.get(0).getDescription());
        assertEquals("ACTIVO", result.get(1).getDescription());
        // verifico que el metodo findAll se haya invocado
        verify(statusRepositoryPort).findAll();
    }

    @Test
    @DisplayName("Prueba de obtencion de un Status con un id válido desde el UseCase")
    public void testGetEstadoByIdFound(){
        Status estado = new Status(2L, "ACTIVO");

        when(statusRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Status> estadoEncontrado = retrieveStatusUseCase.getById(2L);
        assertTrue(estadoEncontrado.isPresent());
        assertEquals("ACTIVO", estadoEncontrado.get().getDescription());
        verify(statusRepositoryPort).findById(2L);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Status con un id NO válido desde el UseCase")
    public void testBuscarEstadoPorIdIncorrecto(){
        Status estado = new Status(2L, "ACTIVO");

        when(statusRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Status> estadoEncontrado = retrieveStatusUseCase.getById(3L);
        assertTrue(estadoEncontrado.isEmpty());
        verify(statusRepositoryPort).findById(3L);
    }

}
