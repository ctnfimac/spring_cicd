package com.cperalta.tienda.integration;

import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.PersonaRepository;
import com.cperalta.tienda.respository.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.springframework.test.web.servlet.MockMvc;



@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonaIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    private Rol rol1;
    private Rol rol2;
    private Estado estado1;
    private Estado estado2;

    @BeforeEach
    void setup() {
        // Limpiar y agregar datos de prueba en la base de datos H2
        this.rol1 = Rol.builder()
                .descripcion("ADMIN")
                .build();

        this.rol2 = Rol.builder()
                .descripcion("COMPRADOR")
                .build();

        rolRepository.save(rol1);
        rolRepository.save(rol2);

        this.estado1 = Estado.builder()
                .descripcion("ACTIVO")
                .build();

        this.estado2 = Estado.builder()
                .descripcion("BLOQUEADO")
                .build();

        estadoRepository.save(estado1);
        estadoRepository.save(estado2);

        personaRepository.deleteAll();
        Persona persona_1 = Persona.builder()
                .nombre("juan")
                .apellido("Pérez")
                .contrasenia("juan")
                .rol(rol1)
                .estado(estado1)
                .build();

        Persona persona_2 = Persona.builder()
                .nombre("Camilo")
                .apellido("Sesto")
                .contrasenia("camilo")
                .rol(rol2)
                .estado(estado2)
                .build();
        personaRepository.save(persona_1);
        personaRepository.save(persona_2);
    }

    @Test
    public void testGetAllPersonas() throws Exception {
        mockMvc.perform(get("/api/persona")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("juan"))
                .andExpect(jsonPath("$[0].apellido").value("Pérez"))
                .andExpect(jsonPath("$[1].nombre").value("Camilo"))
                .andExpect(jsonPath("$[1].apellido").value("Sesto"));
    }
}
