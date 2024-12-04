package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EstadoRepositoryTest {
    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){
        Estado estado = Estado.builder()
                .descripcion("ACTIVO")
                .build();
        testEntityManager.persist(estado);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id válido")
    public void testBuscarEstadoPorIdCorrecto(){
        Optional<Estado> estado = estadoRepository.findById(1L);
        assertTrue(estado.isPresent());
        assertEquals("ACTIVO", estado.get().getDescripcion());
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con un id NO válido")
    public void testBuscarEstadoPorIdIncorrecto(){
        Optional<Estado> estado = estadoRepository.findById(3L);
        assertFalse(estado.isPresent());
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con una descripción válida")
    public void testBuscarEstadoPorDescripcionCorrecto(){
        Optional<Estado> estado = estadoRepository.getEstadoByDescripcion("ACTIVO");
        assertTrue(estado.isPresent());
        assertEquals("ACTIVO", estado.get().getDescripcion());
    }

    @Test
    @DisplayName("Prueba de obtencion de un Estado con una descripción NO válida")
    public void testBuscarEstadoPorDescripcionIncorrecto(){
        Optional<Estado> estado = estadoRepository.getEstadoByDescripcion("BLOQUEADO");
        assertFalse(estado.isPresent());
    }

}
