package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.in.estado.RetrieveEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
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
    private EstadoRepositoryPort estadoRepositoryPort;

    @InjectMocks
    private RetrieveEstadoUseCaseImpl retrieveEstadoUseCase;

    @Test
    @DisplayName("Prueba de obtencion de todos los estados desde el UseCase")
    void testGetAllEstadosFound() {
        // Configuracion del mock para devolver una lista de estados
        List<Estado> estados = Arrays.asList(
                new Estado(1L, "BLOQUEADO"),
                new Estado(2L, "ACTIVO")
        );
        when(estadoRepositoryPort.findAll()).thenReturn(estados);

        //List<Estado> result = estadoRepositoryPort.findAll();
        List<Estado> result = retrieveEstadoUseCase.getAllEstados();

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(2, result.size());
        assertEquals("BLOQUEADO", result.get(0).getDescripcion());
        assertEquals("ACTIVO", result.get(1).getDescripcion());
        // verifico que el metodo findAll se haya invocado
        verify(estadoRepositoryPort).findAll();
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id válido desde el UseCase")
    public void testGetEstadoByIdFound(){
        Estado estado = new Estado(2L, "ACTIVO");

        when(estadoRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Estado> estadoEncontrado = retrieveEstadoUseCase.getEstadoById(2L);
        assertTrue(estadoEncontrado.isPresent());
        assertEquals("ACTIVO", estadoEncontrado.get().getDescripcion());
        verify(estadoRepositoryPort).findById(2L);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id NO válido desde el UseCase")
    public void testBuscarEstadoPorIdIncorrecto(){
        Estado estado = new Estado(2L, "ACTIVO");

        when(estadoRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Estado> estadoEncontrado = retrieveEstadoUseCase.getEstadoById(3L);
        assertTrue(estadoEncontrado.isEmpty());
        verify(estadoRepositoryPort).findById(3L);
    }

}
