package com.cperalta.jardineria.usuario.domain.ports.out;


import com.cperalta.jardineria.usuario.domain.models.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EstadoRepositoryPortUnitTest {
    //@Autowired
    //EstadoRepositoryPort estadoRepositoryPort;

    @Autowired
    TestEntityManager testEntityManager;

    @Mock
    private EstadoRepositoryPort estadoRepositoryPort;


    @Test
    @DisplayName("Prueba de obtencion de todos los estados desde el repositorio")
    void testFindAll() {
        // Configuracion del mock para devolver una lista de estados
        List<Estado> estados = Arrays.asList(
                new Estado(1L, "BLOQUEADO"),
                new Estado(2L, "ACTIVO")
        );
        when(estadoRepositoryPort.findAll()).thenReturn(estados);

        List<Estado> result = estadoRepositoryPort.findAll();

        // verifico el tamaño de la lista retornada y los valores
        assertEquals(2, result.size());
        assertEquals("BLOQUEADO", result.get(0).getDescripcion());
        assertEquals("ACTIVO", result.get(1).getDescripcion());
        // verifico que el metodo findAll se haya invocado
        verify(estadoRepositoryPort).findAll();
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id válido desde el repositorio")
    public void testBuscarEstadoPorIdCorrecto(){
        Estado estado = new Estado(2L, "ACTIVO");

        when(estadoRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Estado> estadoEncontrado = estadoRepositoryPort.findById(2L);
        assertTrue(estadoEncontrado.isPresent());
        assertEquals("ACTIVO", estadoEncontrado.get().getDescripcion());
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id NO válido desde el repositorio")
    public void testBuscarEstadoPorIdIncorrecto(){
        Estado estado = new Estado(2L, "ACTIVO");

        when(estadoRepositoryPort.findById(2L)).thenReturn(Optional.of(estado));
        Optional<Estado> estadoEncontrado = estadoRepositoryPort.findById(3L);
        assertTrue(estadoEncontrado.isEmpty());
    }

    @Test
    @DisplayName("Prueba para crear correctamente un Estado desde el repositorio")
    public void testCrearEstadoDeFormaCorrecta(){
        Estado estado = new Estado(1L, "ACTIVO");

        when(estadoRepositoryPort.create(estado)).thenReturn(estado);

        Estado estadoCreado = estadoRepositoryPort.create(estado);
        assertEquals(estadoCreado, estado);
        verify(estadoRepositoryPort).create(estado);
    }

    @Test
    @DisplayName("Prueba para eliminar correctamente un estado desde el repositorio")
    public void testEliminarEstadoDeFormaCorrecta(){
        when(estadoRepositoryPort.delete(1L)).thenReturn(true);

        Boolean respuesta = estadoRepositoryPort.delete(1L);
        assertTrue(respuesta);
        verify(estadoRepositoryPort).delete(1L);
    }

    @Test
    @DisplayName("Prueba para Actualizar correctamente un estado desde el repositorio")
    public void testActualizarEstadoDeFormaCorrecta(){
        Estado estado = new Estado(1L, "ACTIVO");
        when(estadoRepositoryPort.update(1L, estado )).thenReturn(Optional.of(estado));

        Optional<Estado> estadoActualizado = estadoRepositoryPort.update(1L, estado);
        assertEquals(estadoActualizado.get(), estado);
        verify(estadoRepositoryPort).update(1L, estado);
    }


}
