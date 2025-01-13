package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.input.estado.CreateEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.estado.DeleteEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.estado.RetrieveEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.estado.UpdateEstadoUseCase;
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
    private RetrieveEstadoUseCase retrieveEstadoUseCase;
    @Mock
    private CreateEstadoUseCase createEstadoUseCase;
    @Mock
    private DeleteEstadoUseCase deleteEstadoUseCase;
    @Mock
    private UpdateEstadoUseCase updateEstadoUseCase;

    @InjectMocks
    private EstadoService estadoService;

    @Test
    @DisplayName("Prueba de obtencion de todos los estados desde el Service")
    void testGetAllEstadosFound() {
        List<Estado> estados = Arrays.asList(
                new Estado(1L, "BLOQUEADO"),
                new Estado(2L, "ACTIVO")
        );
        when(retrieveEstadoUseCase.getAllEstados()).thenReturn(estados);

        List<Estado> result = estadoService.getAllEstados();

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(2, result.size());
        assertEquals("BLOQUEADO", result.get(0).getDescripcion());
        assertEquals("ACTIVO", result.get(1).getDescripcion());
        // verifico que el metodo getAllEstados() se haya invocado
        verify(retrieveEstadoUseCase).getAllEstados();
    }

    @Test
    @DisplayName("Prueba de obtención de un Estado por Id desde el Service de forma correcta")
    void testGetEstadoByIdFound() {
        Estado estado = new Estado(1L, "ACTIVO");
        when(retrieveEstadoUseCase.getEstadoById(1L)).thenReturn(Optional.of(estado));

        Optional<Estado> result = estadoService.getEstadoById(1L);

        assertEquals(estado, result.get());
        // verifico que el metodo getAllEstados() se haya invocado
        verify(retrieveEstadoUseCase).getEstadoById(1L);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado por Id desde el Service de forma incorrecta")
    void testGetEstadoByIdNotFound() {
        Estado estado = new Estado(1L, "ACTIVO");
        when(retrieveEstadoUseCase.getEstadoById(2L)).thenReturn(Optional.empty());

        Optional<Estado> result = estadoService.getEstadoById(2L);

        assertEquals(Optional.empty(), result);
        // verifico que el metodo getAllEstados() se haya invocado
        verify(retrieveEstadoUseCase).getEstadoById(2L);
    }

    @Test
    @DisplayName("Prueba creación de Estado desde el Service de forma correcta")
    void testCreateEstadoFound() {
        Estado estado = new Estado(1L, "ACTIVO");
        when(createEstadoUseCase.createEstado(estado)).thenReturn(estado);

        Estado result = estadoService.createEstado(estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(estado, result);
        verify(createEstadoUseCase).createEstado(estado);
    }

    @Test
    @DisplayName("Prueba eliminación de un Estado desde el Service de forma correcta")
    void testDeleteEstadoFound() {
        when(deleteEstadoUseCase.deleteEstado(1L)).thenReturn(true);

        Boolean result = estadoService.deleteEstado(1L);

        // verifico el tamaño de la lista retornada y los valores
        assertTrue(result);
        verify(deleteEstadoUseCase).deleteEstado(1L);
    }

    @Test
    @DisplayName("Prueba eliminación de un Estado desde el Service de forma incorrecta")
    void testDeleteEstadoNotFound() {
        when(deleteEstadoUseCase.deleteEstado(2L)).thenReturn(false);

        Boolean result = estadoService.deleteEstado(2L);

        // verifico el tamaño de la lista retornada y los valores
        assertFalse(result);
        verify(deleteEstadoUseCase).deleteEstado(2L);
    }

    @Test
    @DisplayName("Prueba Modificación de un Estado desde el Service de forma correcta")
    void testUpdateEstadoFound() {
        Estado estado = new Estado(1L, "NUEVO");
        when(updateEstadoUseCase.updateEstado(1L, estado)).thenReturn(Optional.of(estado));

        Optional<Estado> result = estadoService.updateEstado(1L, estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(result.get(), estado);
        verify(updateEstadoUseCase).updateEstado(1L, estado);
    }

    @Test
    @DisplayName("Prueba Modificación de un Estado desde el Service de forma incorrecta")
    void testUpdateEstadoNotFound() {
        Estado estado = new Estado(100L, "NUEVO");
        when(updateEstadoUseCase.updateEstado(1L, estado)).thenReturn(Optional.empty());

        Optional<Estado> result = estadoService.updateEstado(100L, estado);

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(result, Optional.empty());
        verify(updateEstadoUseCase).updateEstado(100L, estado);
    }
}
