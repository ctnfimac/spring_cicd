package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.input.status.CreateStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.status.DeleteStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.status.RetrieveStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.status.UpdateStatusUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
public class EstadoServiceUnitTest {
    @Mock
    private RetrieveStatusUseCase retrieveStatusUseCase;
    @Mock
    private CreateStatusUseCase createStatusUseCase;
    @Mock
    private DeleteStatusUseCase deleteStatusUseCase;
    @Mock
    private UpdateStatusUseCase updateStatusUseCase;

    @InjectMocks
    private StatusService statusService;

    @Test
    @DisplayName("Prueba de obtencion de todos los estados desde el Service")
    void testGetAllEstadosFound() {
        List<Status> estados = Arrays.asList(
                new Status(1L, "BLOQUEADO"),
                new Status(2L, "ACTIVO")
        );
        when(retrieveStatusUseCase.getAll()).thenReturn(estados);

        List<Status> result = statusService.getAll();

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(2, result.size());
        assertEquals("BLOQUEADO", result.get(0).getDescription());
        assertEquals("ACTIVO", result.get(1).getDescription());
        // verifico que el metodo getAll() se haya invocado
        verify(retrieveStatusUseCase).getAll();
    }

    @Test
    @DisplayName("Prueba de obtención de un Status por Id desde el Service de forma correcta")
    void testGetEstadoByIdFound() {
        Status estado = new Status(1L, "ACTIVO");
        when(retrieveStatusUseCase.getById(1L)).thenReturn(Optional.of(estado));

        Optional<Status> result = statusService.getById(1L);

        assertEquals(estado, result.get());
        // verifico que el metodo getAll() se haya invocado
        verify(retrieveStatusUseCase).getById(1L);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Status por Id desde el Service de forma incorrecta")
    void testGetEstadoByIdNotFound() {
        Status estado = new Status(1L, "ACTIVO");
        when(retrieveStatusUseCase.getById(2L)).thenReturn(Optional.empty());

        Optional<Status> result = statusService.getById(2L);

        assertEquals(Optional.empty(), result);
        // verifico que el metodo getAll() se haya invocado
        verify(retrieveStatusUseCase).getById(2L);
    }

    @Test
    @DisplayName("Prueba creación de Status desde el Service de forma correcta")
    void testCreateEstadoFound() {
        Status estado = new Status(1L, "ACTIVO");
        when(createStatusUseCase.create(estado)).thenReturn(estado);

        Status result = statusService.create(estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(estado, result);
        verify(createStatusUseCase).create(estado);
    }

    @Test
    @DisplayName("Prueba eliminación de un Status desde el Service de forma correcta")
    void testDeleteEstadoFound() {
        when(deleteStatusUseCase.delete(1L)).thenReturn(true);

        Boolean result = statusService.delete(1L);

        // verifico el tamaño de la lista retornada y los valores
        assertTrue(result);
        verify(deleteStatusUseCase).delete(1L);
    }

    @Test
    @DisplayName("Prueba eliminación de un Status desde el Service de forma incorrecta")
    void testDeleteEstadoNotFound() {
        when(deleteStatusUseCase.delete(2L)).thenReturn(false);

        Boolean result = statusService.delete(2L);

        // verifico el tamaño de la lista retornada y los valores
        assertFalse(result);
        verify(deleteStatusUseCase).delete(2L);
    }

    @Test
    @DisplayName("Prueba Modificación de un Status desde el Service de forma correcta")
    void testUpdateEstadoFound() {
        Status estado = new Status(1L, "NUEVO");
        when(updateStatusUseCase.update(1L, estado)).thenReturn(Optional.of(estado));

        Optional<Status> result = statusService.update(1L, estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(result.get(), estado);
        verify(updateStatusUseCase).update(1L, estado);
    }

    @Test
    @DisplayName("Prueba Modificación de un Status desde el Service de forma incorrecta")
    void testUpdateEstadoNotFound() {
        Status estado = new Status(100L, "NUEVO");
        when(updateStatusUseCase.update(1L, estado)).thenReturn(Optional.empty());

        Optional<Status> result = statusService.update(100L, estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(result, Optional.empty());
        verify(updateStatusUseCase).update(100L, estado);
    }
}
