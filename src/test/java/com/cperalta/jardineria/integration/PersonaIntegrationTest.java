package com.cperalta.tienda.integration;

import com.cperalta.tienda.dto.PersonaDTO;
import com.cperalta.tienda.dto.PersonaUpdateDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.PersonaRepository;
import com.cperalta.tienda.respository.RolRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
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
    private Integer personaId_1;
    private Integer personaId_2;


    @BeforeEach
    void setup() {
        personaRepository.deleteAll();
        rolRepository.deleteAll();
        estadoRepository.deleteAll();
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
        persona_1 = personaRepository.save(persona_1);
        persona_2 = personaRepository.save(persona_2);

        personaId_1 = persona_1.getId();
        personaId_2 = persona_2.getId();

    }

    @Test
    @DisplayName("Test de integración para verificar la obtención de todas las personas")
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

    @Test
    @DisplayName("Test de integración para verificar la obtención de una persona por ID")
    public void testGetPersonaById() throws Exception {
        mockMvc.perform(get("/api/persona/" + this.personaId_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"));
    }

    @Test
    @DisplayName("Test de integración para verificar la eliminación de una persona por ID")
    public void testDeletePersonaById() throws Exception {
        Long cantidadDeRegistrosAntesDelDelete = personaRepository.count();

        mockMvc.perform(delete("/api/persona/" + this.personaId_2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(personaRepository.existsById(this.personaId_2.longValue()));

        Long cantidadDeRegistrosDespuesDelDelete = personaRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelDelete - 1, cantidadDeRegistrosDespuesDelDelete);
    }

    @Test
    @DisplayName("Test de integración para verificar la creación de una persona nueva")
    public void testPostPersona() throws Exception {
        Long cantidadDeRegistrosAntesDelPost = personaRepository.count();

        PersonaDTO personaNueva = new PersonaDTO();
        personaNueva.setNombre("Manolo");
        personaNueva.setApellido("Galvan");
        personaNueva.setContrasenia("manolo");
        personaNueva.setRolId(this.rol2.getId().intValue());
        personaNueva.setEstadoId(this.estado1.getId());

        String personaNuevaJson = new ObjectMapper().writeValueAsString(personaNueva);

        mockMvc.perform(post("/api/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaNuevaJson))
                .andExpect(status().isCreated());

        Long cantidadDeRegistrosDespuesDelPost = personaRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelPost + 1, cantidadDeRegistrosDespuesDelPost);
    }


    @Test
    @DisplayName("Test de integración para verificar la actualización de una persona existente")
    public void testUpdatePersona() throws Exception {
        PersonaUpdateDTO personaActualizada = new PersonaUpdateDTO();
                personaActualizada.setApellido("Ester");

        String personaActualizadaJson = new ObjectMapper().writeValueAsString(personaActualizada);

        mockMvc.perform(put("/api/persona/{id}", this.personaId_1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personaActualizadaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apellido").value("Ester"));
    }
}
