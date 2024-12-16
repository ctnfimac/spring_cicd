package com.cperalta.tienda.unit.respository;

import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.RolRepository;
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
public class RolRepositoryTest {

    @Autowired
    RolRepository rolRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){
        Rol rol = Rol.builder()
                .descripcion("DELIVERY")
                .build();
        testEntityManager.persist(rol);
    }

    @Test
    @DisplayName("Prueba de obtencion de un Rol con un id válido")
    public void findRolByIdFound(){
        Optional<Rol> rol = rolRepository.findById(1L);
        assertTrue(rol.isPresent());
        assertEquals("DELIVERY", rol.get().getDescripcion());
    }

    @Test
    @DisplayName("Prueba de obtención de un Rol con un id no válido")
    public void findRolbyIdNotFound(){
        Optional<Rol> rol = rolRepository.findById(8L);
        assertEquals(rol, Optional.empty());
    }
}
