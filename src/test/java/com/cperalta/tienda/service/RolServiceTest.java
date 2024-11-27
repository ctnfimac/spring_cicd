package com.cperalta.tienda.service;

import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @InjectMocks
    private RolServiceImpl rolService;

    @Mock
    private RolRepository rolRepository;

    private Rol rol1;
    private Rol rol2;
    private Rol rol3;
    private List<Rol> listaDeRoles;

    @BeforeEach
    void setUp(){
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
    @DisplayName("Obtengo todos los roles")
    public void findAllRol(){
        Mockito.when(rolRepository.findAll()).thenReturn(this.listaDeRoles);
        List<Rol> roles = rolService.getAll();
        assertEquals(roles.size(), listaDeRoles.size());
    }

    @Test
    @DisplayName("Obtengo un rol buscado por ID")
    public void finRolByIdExists(){
        Mockito.when(rolRepository.findById(3L)).thenReturn(Optional.of(this.rol3));
        Rol rol = rolService.getById(3L);
        assertEquals(rol, this.rol3);
        assertEquals("VENDEDOR",rol.getDescripcion());
    }

    @Test
    @DisplayName("Busco un rol por ID pero no existe")
    public void finRolByIdNotExists(){
        Mockito.when(rolRepository.findById(30L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rolService.getById(30L));
        // verifico el mensaje de la excepcion
        assertEquals("El rol con el ID 30 no existe.", exception.getMessage());
        // Verificar que el repositorio fue llamado correctamente
        Mockito.verify(rolRepository, Mockito.times(1)).findById(30L);
    }

}
