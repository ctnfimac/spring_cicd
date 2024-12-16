package com.cperalta.tienda.unit.controller;

import com.cperalta.tienda.config.NoSecurityConfig;
import com.cperalta.tienda.controller.RolController;
import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.service.RolService;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RolController.class)
@ActiveProfiles("test")
@Import(NoSecurityConfig.class) // Incluye la configuración personalizada en la carpeta config(deshabilita el security)
class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RolService rolService;

    private Rol rol1;
    private Rol rol2;
    private Rol rol3;
    private List<Rol> listaDeRoles;

    @BeforeEach
    void setUp() {
        this.rol1 = Rol.builder()
                .descripcion("ADMIN")
                .build();

        this.rol2 = Rol.builder()
                .descripcion("COMPRADOR")
                .build();

        this.rol3 = Rol.builder()
                .descripcion("VENDEDOR")
                .build();

        this.listaDeRoles = Arrays.asList(this.rol1, this.rol2, this.rol3);
    }

    @Test
    @DisplayName("Obtener todos los roles de forma correcta")
    public void testObtenerTodosLosRoles() throws Exception {
        Mockito.when(rolService.getAll()).thenReturn(this.listaDeRoles);
        mockMvc.perform(get("/api/rol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].descripcion", is("ADMIN")))
                .andExpect(jsonPath("$[1].descripcion", is("COMPRADOR")))
                .andExpect(jsonPath("$[2].descripcion", is("VENDEDOR")));
    }

    @Test
    @DisplayName("Obtener un Rol por ID de forma correcta")
    public void testObtenerRolPorIdCorrecto() throws Exception {
        Mockito.when(rolService.getById(2L)).thenReturn(this.rol2);
        mockMvc.perform(get("/api/rol/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("COMPRADOR"));
    }


    @Test
    @DisplayName("Error al obtener un Rol con un ID que no existe")
    public void testObtenerRolPorIdIncorrecto() throws Exception {
        Mockito.when(rolService.getById(5L)).thenThrow(new IllegalArgumentException("El rol con el ID 5 no existe."));
        mockMvc.perform(get("/api/rol/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("El rol con el ID 5 no existe."));
    }

    @Test
    @DisplayName("Eliminar correctamente un Rol")
    public void testEliminarRolCorrectamente() throws Exception {
        Mockito.when(rolService.delete(3L)).thenReturn(true);
        mockMvc.perform(delete("/api/rol/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Eliminar Incorrectamente un Rol")
    public void testEliminarRolInCorrectamente() throws Exception {
        Mockito.when(rolService.delete(5L)).thenReturn(false);
        mockMvc.perform(delete("/api/rol/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Crear correctamente un Rol")
    public void testCrearRolNuevoCorrectamente() throws Exception {
        RolDTO rolNuevoDTO = new RolDTO();
        rolNuevoDTO.setDescripcion("SUPER ADMIN");

        Rol resultado = Rol.builder()
                .id(5L)
                .descripcion("SUPER ADMIN")
                .build();

        Mockito.when(rolService.create(Mockito.any(RolDTO.class))).thenReturn(resultado);

        // Convertir RolDTO a JSON
        String rolNuevoJson = new ObjectMapper().writeValueAsString(resultado);

        mockMvc.perform(post("/api/rol").contentType(MediaType.APPLICATION_JSON)
                        .content(rolNuevoJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("SUPER ADMIN"))
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    @DisplayName("Crear incorrectamente un Rol")
    public void testCrearRolNuevoIncorrectamente() throws Exception {
        RolDTO rolNuevoDTO = new RolDTO();
        String rolNuevoJson = new ObjectMapper().writeValueAsString(rolNuevoDTO);

        Mockito.when(rolService.create(rolNuevoDTO)).thenReturn(null);

        mockMvc.perform(post("/api/rol").contentType(MediaType.APPLICATION_JSON)
                        .content(rolNuevoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Actualizar un Rol correctamente")
    public void testActualizarRolCorrectamente() throws Exception {
        Long id = 1L;

        RolDTO rolDatosNuevosDTO = new RolDTO();
        rolDatosNuevosDTO.setDescripcion("TIENDA");

        Rol rolActualizado = Rol.builder()
                .id(id)
                .descripcion("TIENDA")
                .build();

        Mockito.when(rolService.update(Mockito.any(Long.class), Mockito.any(RolDTO.class))).thenReturn(rolActualizado);

        // Convertir RolDTO a JSON
        String rolDatosNuevosJson = new ObjectMapper().writeValueAsString(rolDatosNuevosDTO);

        ResultActions response =  mockMvc.perform(put("/api/rol/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(rolDatosNuevosJson));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.descripcion").value("TIENDA"))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Actualizar un Rol de forma incorrecta")
    public void testActualizarRolInorrectamente() throws Exception {
        Long id = 8L;

        RolDTO rolDatosNuevosDTO = new RolDTO();
        rolDatosNuevosDTO.setDescripcion("TIENDA");

        Mockito.when(rolService.update(Mockito.any(Long.class), Mockito.any(RolDTO.class))).thenReturn(null);

        // Convertir RolDTO a JSON
        String rolDatosNuevosJson = new ObjectMapper().writeValueAsString(rolDatosNuevosDTO);

        ResultActions response =  mockMvc.perform(put("/api/rol/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(rolDatosNuevosJson));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
