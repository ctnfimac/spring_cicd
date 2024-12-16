package com.cperalta.tienda.unit.controller;


import com.cperalta.tienda.config.NoSecurityConfig;
import com.cperalta.tienda.controller.EstadoController;
import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.service.EstadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstadoController.class)
@ActiveProfiles("test")
@Import(NoSecurityConfig.class) // Deshabilito el security
public class EstadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadoService estadoService;

    private Estado estado1;
    private Estado estado2;
    private List<Estado> listaDeRoles;

    @BeforeEach
    void setUp() {
        this.estado1 = Estado.builder()
                .descripcion("ACTIVO")
                .build();

        this.estado2 = Estado.builder()
                .descripcion("BLOQUEADO")
                .build();

        this.listaDeRoles = Arrays.asList(this.estado1, this.estado2);
    }

    @Test
    @DisplayName("Obtengo todos los Estados")
    public void testObtenerTodosLosEstados() throws Exception {
        Mockito.when(estadoService.getAll()).thenReturn(this.listaDeRoles);
        mockMvc.perform(get("/api/estado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Obtengo un Estado por ID de forma correcta")
    public void testObtenerEstadoPorIdCorrecto() throws Exception {
        Mockito.when(estadoService.getById(1L)).thenReturn(this.estado1);
        mockMvc.perform(get("/api/estado/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion", is("ACTIVO")));
    }


    @Test
    @DisplayName("Error al obtener un Estado con un ID que no existe")
    public void testObtenerEstadoPorIdIncorrecto() throws Exception {
        Mockito.when(estadoService.getById(5L)).thenThrow(new IllegalArgumentException("El Estado con el ID 5 no existe"));
        mockMvc.perform(get("/api/estado/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("El Estado con el ID 5 no existe"));
    }


    @Test
    @DisplayName("Eliminar correctamente un Estado")
    public void testEliminarRolCorrectamente() throws Exception {
        Mockito.when(estadoService.delete(2L)).thenReturn(true);
        mockMvc.perform(delete("/api/estado/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Eliminar Incorrectamente un Estado")
    public void testEliminarEstadoInCorrectamente() throws Exception {
        Mockito.when(estadoService.delete(5L)).thenReturn(false);
        mockMvc.perform(delete("/api/estado/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Crear correctamente un Estado")
    public void testCrearEstadoNuevoCorrectamente() throws Exception {
        EstadoDTO estadoNuevoDTO = new EstadoDTO();
        estadoNuevoDTO.setDescripcion("PENDIENTE");

        Estado resultado = Estado.builder()
                .id(3)
                .descripcion("PENDIENTE")
                .build();

        Mockito.when(estadoService.create(Mockito.any(EstadoDTO.class))).thenReturn(resultado);

        // Convertir RolDTO a JSON
        String estadoNuevoJson = new ObjectMapper().writeValueAsString(resultado);

        mockMvc.perform(post("/api/estado").contentType(MediaType.APPLICATION_JSON)
                        .content(estadoNuevoJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("PENDIENTE"))
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    @DisplayName("Crear incorrectamente un Estado")
    public void testCrearEstadoNuevoIncorrectamente() throws Exception {
        EstadoDTO estadoNuevoDTO = new EstadoDTO();
        String rolNuevoJson = new ObjectMapper().writeValueAsString(estadoNuevoDTO);

        Mockito.when(estadoService.create(estadoNuevoDTO)).thenReturn(null);

        mockMvc.perform(post("/api/estado").contentType(MediaType.APPLICATION_JSON)
                        .content(rolNuevoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Actualizar un Estado correctamente")
    public void testActualizarEstadoCorrectamente() throws Exception {
        Integer id = 1;

        EstadoDTO estadoDatosNuevosDTO = new EstadoDTO();
        estadoDatosNuevosDTO.setDescripcion("INACTIVO");

        Estado estadoActualizado = Estado.builder()
                .id(id)
                .descripcion("INACTIVO")
                .build();

        Mockito.when(estadoService.update(Mockito.any(Long.class), Mockito.any(EstadoDTO.class))).thenReturn(estadoActualizado);

        // Convertir RolDTO a JSON
        String estadoDatosNuevosJson = new ObjectMapper().writeValueAsString(estadoDatosNuevosDTO);

        ResultActions response =  mockMvc.perform(put("/api/estado/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(estadoDatosNuevosJson));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.descripcion").value("INACTIVO"))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Actualizar un Estado de forma incorrecta")
    public void testActualizarEstadoInorrectamente() throws Exception {
        Long id = 8L;

        EstadoDTO estadoDatosNuevosDTO = new EstadoDTO();
        estadoDatosNuevosDTO.setDescripcion("BLOQUEADO");

        Mockito.when(estadoService.update(Mockito.any(Long.class), Mockito.any(EstadoDTO.class))).thenReturn(null);

        // Convertir RolDTO a JSON
        String rolDatosNuevosJson = new ObjectMapper().writeValueAsString(estadoDatosNuevosDTO);

        ResultActions response =  mockMvc.perform(put("/api/estado/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(rolDatosNuevosJson));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
