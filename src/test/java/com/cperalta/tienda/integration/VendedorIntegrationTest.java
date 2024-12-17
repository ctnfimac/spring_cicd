package com.cperalta.tienda.integration;

import com.cperalta.tienda.dto.*;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.entity.Vendedor;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.PersonaRepository;
import com.cperalta.tienda.respository.RolRepository;
import com.cperalta.tienda.respository.VendedorRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VendedorIntegrationTest {

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
    private VendedorService vendedorService;

    private Rol rol1;
    private Rol rol2;
    private Estado estado1;
    private Estado estado2;
    private Integer vendedorId_1;
    private Integer vendedorId_2;



    @BeforeEach
    void setup() {
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

        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setEmail("juan@gmail.com");
        vendedorDTO.setTelefono("1187226941");
        vendedorDTO.setNombre("juan");
        vendedorDTO.setApellido("Pérez");
        vendedorDTO.setContrasenia("juan");
        vendedorDTO.setRolId(this.rol1.getId().intValue());
        vendedorDTO.setEstadoId(this.estado1.getId());

        VendedorDTO vendedor2DTO = new VendedorDTO();
        vendedor2DTO.setEmail("camilo@gmail.com");
        vendedor2DTO.setTelefono("1160253198");
        vendedor2DTO.setNombre("camilo");
        vendedor2DTO.setApellido("Sesto");
        vendedor2DTO.setContrasenia("camilo");
        vendedor2DTO.setRolId(this.rol2.getId().intValue());
        vendedor2DTO.setEstadoId(this.estado1.getId());

        Vendedor vendedor1 = vendedorService.create(vendedorDTO);
        Vendedor vendedor2 = vendedorService.create(vendedor2DTO);

        this.vendedorId_1 = vendedor1.getId();
        this.vendedorId_2 = vendedor2.getId();
    }

    @Test
    @DisplayName("Test de integración para verificar la obtención de todos los Vendedores")
    public void testGetAllVendedores() throws Exception {
        mockMvc.perform(get("/api/vendedor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("juan@gmail.com"))
                .andExpect(jsonPath("$[0].telefono").value("1187226941"))
                .andExpect(jsonPath("$[1].email").value("camilo@gmail.com"))
                .andExpect(jsonPath("$[1].telefono").value("1160253198"));
    }

    @Test
    @DisplayName("Test de integración para verificar la obtención de un Vendedor por ID")
    public void testGetVendedorById() throws Exception {
        mockMvc.perform(get("/api/vendedor/" + this.vendedorId_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("juan"))
                .andExpect(jsonPath("$.telefono").value("1187226941"));
    }

    @Test
    @DisplayName("Test de integración para verificar la eliminación de un Veneddor por ID")
    public void testDeleteVendedorById() throws Exception {
        Long cantidadDeRegistrosAntesDelDelete = vendedorRepository.count();

        mockMvc.perform(delete("/api/vendedor/" + this.vendedorId_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(vendedorRepository.existsById(this.vendedorId_1.longValue()));

        Long cantidadDeRegistrosDespuesDelDelete = vendedorRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelDelete - 1, cantidadDeRegistrosDespuesDelDelete);
    }

    @Test
    @DisplayName("Test de integración para verificar la creación de un Vendedor nuevo")
    public void testPostVendedor() throws Exception {
        Long cantidadDeRegistrosAntesDelPost = personaRepository.count();

        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setEmail("manolo@gmail.com");
        vendedorDTO.setTelefono("1187523641");
        vendedorDTO.setNombre("Manolo");
        vendedorDTO.setApellido("Galvan");
        vendedorDTO.setContrasenia("manolo");
        vendedorDTO.setRolId(this.rol1.getId().intValue());
        vendedorDTO.setEstadoId(this.estado1.getId());

        String vendedorNuevoJson = new ObjectMapper().writeValueAsString(vendedorDTO);

        mockMvc.perform(post("/api/vendedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vendedorNuevoJson))
                .andExpect(status().isCreated());

        Long cantidadDeRegistrosDespuesDelPost = personaRepository.count();
        assertEquals(cantidadDeRegistrosAntesDelPost + 1, cantidadDeRegistrosDespuesDelPost);
    }


    @Test
    @DisplayName("Test de integración para verificar la actualización de un vendedor existente")
    public void testUpdateVendedor() throws Exception {
        VendedorUpdateDTO vendedorUpdateDTO = new VendedorUpdateDTO();
        vendedorUpdateDTO.setTelefono("1155556666");
        vendedorUpdateDTO.setEmail("manolo@hotmail.com");

        String vendedorUpdateDTOJson = new ObjectMapper().writeValueAsString(vendedorUpdateDTO);

        mockMvc.perform(put("/api/vendedor/{id}", this.vendedorId_2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vendedorUpdateDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefono").value("1155556666"))
                .andExpect(jsonPath("$.email").value("manolo@hotmail.com"));
    }

}
