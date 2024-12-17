package com.cperalta.tienda.integration;


import com.cperalta.tienda.dto.CompradorDTO;
import com.cperalta.tienda.dto.CompradorUpdateDTO;
import com.cperalta.tienda.dto.VendedorDTO;
import com.cperalta.tienda.dto.VendedorUpdateDTO;
import com.cperalta.tienda.entity.Comprador;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.entity.Vendedor;
import com.cperalta.tienda.respository.*;
import com.cperalta.tienda.service.CompradorService;
import com.cperalta.tienda.service.VendedorService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CompradorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private CompradorRepository compradorRepository;
    @Autowired
    private CompradorService compradorService;

    private Rol rol1;
    private Rol rol2;
    private Estado estado1;
    private Estado estado2;
    private Integer compradorId_1;
    private Integer compradorId_2;

    @BeforeEach
    void setup() {
        compradorRepository.deleteAll();
        vendedorRepository.deleteAll();
        personaRepository.deleteAll();
        rolRepository.deleteAll();
        estadoRepository.deleteAll();

        this.rol1 = Rol.builder().descripcion("ADMIN").build();
        this.rol2 = Rol.builder().descripcion("COMPRADOR").build();
        rolRepository.save(rol1);
        rolRepository.save(rol2);

        this.estado1 = Estado.builder().descripcion("ACTIVO").build();
        this.estado2 = Estado.builder().descripcion("BLOQUEADO").build();
        estadoRepository.save(estado1);
        estadoRepository.save(estado2);

        CompradorDTO compradorDTO = new CompradorDTO();
        compradorDTO.setEmail("joseluis@gmail.com");
        compradorDTO.setTelefono("1187226941");
        compradorDTO.setNombre("Jose Luis");
        compradorDTO.setApellido("Perales");
        compradorDTO.setContrasenia("joseluis");
        compradorDTO.setDireccion("Lacarra 535");
        compradorDTO.setRolId(this.rol2.getId().intValue());
        compradorDTO.setEstadoId(this.estado1.getId());

        CompradorDTO comprador2DTO = new CompradorDTO();
        comprador2DTO.setEmail("miguel@gmail.com");
        comprador2DTO.setTelefono("1122222228");
        comprador2DTO.setNombre("Miguel");
        comprador2DTO.setApellido("Gallardo");
        comprador2DTO.setContrasenia("miguel");
        comprador2DTO.setDireccion("Rivadavia 11700");
        comprador2DTO.setRolId(this.rol2.getId().intValue());
        comprador2DTO.setEstadoId(this.estado2.getId());

        Comprador comprador1 = compradorService.create(compradorDTO);
        Comprador comprador2 = compradorService.create(comprador2DTO);

        this.compradorId_1 = comprador1.getId();
        this.compradorId_2 = comprador2.getId();
    }


    @Test
    @DisplayName("Test de integración para verificar la obtención de todos los Compradores")
    public void testGetAllCompradores() throws Exception {
        mockMvc.perform(get("/api/comprador")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("joseluis@gmail.com"))
                .andExpect(jsonPath("$[0].telefono").value("1187226941"))
                .andExpect(jsonPath("$[0].direccion").value("Lacarra 535"))
                .andExpect(jsonPath("$[0].apellido").value("Perales"));
    }

    @Test
    @DisplayName("Test de integración para verificar la obtención de un Comprador por ID")
    public void testGetCompradorById() throws Exception {
        mockMvc.perform(get("/api/comprador/" + this.compradorId_2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Miguel"))
                .andExpect(jsonPath("$.telefono").value("1122222228"));
    }

    @Test
    @DisplayName("Test de integración para verificar la eliminación de un Comprador por ID")
    public void testDeleteCompradorById() throws Exception {
        Long cantidadDeRegistrosAntesDelDelete = compradorRepository.count();

        mockMvc.perform(delete("/api/comprador/" + this.compradorId_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(compradorRepository.existsById(this.compradorId_1.longValue()));

        Long cantidadDeRegistrosDespuesDelDelete = compradorRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelDelete - 1, cantidadDeRegistrosDespuesDelDelete);
    }

    @Test
    @DisplayName("Test de integración para verificar la creación de un Comprador nuevo")
    public void testPostComprador() throws Exception {
        Long cantidadDeRegistrosAntesDelPost = compradorRepository.count();

        CompradorDTO compradorDTO = new CompradorDTO();
        compradorDTO.setEmail("donald@gmail.com");
        compradorDTO.setTelefono("1147653208");
        compradorDTO.setNombre("Test");
        compradorDTO.setApellido("Trump");
        compradorDTO.setContrasenia("donald");
        compradorDTO.setDireccion("New York 321");
        compradorDTO.setRolId(this.rol1.getId().intValue());
        compradorDTO.setEstadoId(this.estado1.getId());

        String compradorDTOJson = new ObjectMapper().writeValueAsString(compradorDTO);

        mockMvc.perform(post("/api/comprador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(compradorDTOJson))
                .andExpect(status().isCreated());

        Long cantidadDeRegistrosDespuesDelPost = compradorRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelPost + 1, cantidadDeRegistrosDespuesDelPost);
    }


    @Test
    @DisplayName("Test de integración para verificar la actualización de un Comprador existente")
    public void testUpdateComprador() throws Exception {
        CompradorUpdateDTO compradorUpdateDTO = new CompradorUpdateDTO();
        compradorUpdateDTO.setEmail("donaldtrump@hotmail.com");

        String compradorUpdateDTOJson = new ObjectMapper().writeValueAsString(compradorUpdateDTO);

        mockMvc.perform(put("/api/comprador/{id}", this.compradorId_1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(compradorUpdateDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("donaldtrump@hotmail.com"));
    }


}
