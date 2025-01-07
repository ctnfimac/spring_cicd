package com.cperalta.tienda.unit.service;

import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.RolRepository;
import com.cperalta.tienda.service.RolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RolServiceUnitTest {

    @InjectMocks
    private RolServiceImpl rolService;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private ModelMapper mapper;

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
    @DisplayName("Obtengo todos los roles")
    public void findAllRol() {
        Mockito.when(rolRepository.findAll()).thenReturn(this.listaDeRoles);
        List<Rol> roles = rolService.getAll();
        assertEquals(roles.size(), listaDeRoles.size());
    }

    @Test
    @DisplayName("Obtengo un rol buscado por ID")
    public void finRolByIdExists() {
        Mockito.when(rolRepository.findById(3L)).thenReturn(Optional.of(this.rol3));
        Rol rol = rolService.getById(3L);
        assertEquals(rol, this.rol3);
        assertEquals("VENDEDOR", rol.getDescripcion());
    }

    @Test
    @DisplayName("Busco un rol por ID pero no existe")
    public void finRolByIdNotExists() {
        Mockito.when(rolRepository.findById(30L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rolService.getById(30L));
        // verifico el mensaje de la excepcion
        assertEquals("El rol con el ID 30 no existe.", exception.getMessage());
        // Verificar que el repositorio fue llamado correctamente
        Mockito.verify(rolRepository, Mockito.times(1)).findById(30L);
    }

    @Test
    @DisplayName("Elimino un Rol por el ID")
    public void deleteRolByIdFound() {
        Mockito.when(rolRepository.existsById(1L)).thenReturn(true);
        Mockito.when(rolRepository.getById(1L)).thenReturn(this.rol1);
        boolean resultado = rolService.delete(1L);

        assertTrue(resultado);

        // verifico si los métodos del repositorio fueron llamados
        Mockito.verify(rolRepository, Mockito.times(1)).existsById(1L);
        Mockito.verify(rolRepository, Mockito.times(1)).getById(1L);
        Mockito.verify(rolRepository, Mockito.times(1)).delete(this.rol1);
    }

    @Test
    @DisplayName("No puedo eliminar un Rol por un ID erroneo")
    public void deleteRolByIdNotFound() {
        Mockito.when(rolRepository.existsById(1L)).thenReturn(false);
        //Mockito.when(rolRepository.getById(1L)).thenReturn(this.rol1);
        boolean resultado = rolService.delete(1L);

        assertFalse(resultado);

        // verifico si los métodos del repositorio fueron llamados
        Mockito.verify(rolRepository, Mockito.times(1)).existsById(1L);
        Mockito.verify(rolRepository, Mockito.times(0)).getById(1L);
        Mockito.verify(rolRepository, Mockito.times(0)).delete(this.rol1);
    }

    @Test
    @DisplayName("Guardo correctamente un nuevo ROL")
    public void saveRolFound() {
        RolDTO rolNuevoDTO = new RolDTO();
        rolNuevoDTO.setDescripcion("DELIVERY");

        Rol rolNuevoEntity = new Rol();
        rolNuevoEntity.setDescripcion("DELIVERY");

        Rol rolNuevoResultado = new Rol();
        rolNuevoResultado.setId(4L);
        rolNuevoResultado.setDescripcion("DELIVERY");

        List<Rol> roles = new ArrayList<>(Arrays.asList(this.rol1, this.rol2, this.rol3));

        Mockito.when(mapper.map(rolNuevoDTO, Rol.class)).thenReturn(rolNuevoEntity);
        //Mockito.when(rolRepository.save(rolNuevoEntity)).thenReturn(rolNuevoResultado);
        Mockito.when(rolRepository.save(rolNuevoEntity)).thenAnswer( invocation -> {
            Rol nuevo = invocation.getArgument(0);
            nuevo.setId(4L);
            roles.add(nuevo);
            return nuevo;
        });
        Mockito.when(rolRepository.findAll()).thenReturn(roles);

        Rol resultado = rolService.create(rolNuevoDTO);

        assertNotNull(resultado);
        assertEquals(4L, resultado.getId());
        assertEquals("DELIVERY", resultado.getDescripcion());

        // verifico la cantidad nueva de roles
        List<Rol> listaActualizada = rolRepository.findAll();
        assertEquals(4, listaActualizada.size());

        // verifico que se ejecutaron las interacciones de los mocks
        Mockito.verify(mapper, Mockito.times(1)).map(rolNuevoDTO, Rol.class);
        Mockito.verify(rolRepository, Mockito.times(1)).save(rolNuevoEntity);
    }


    @Test
    @DisplayName("Modifico los valores de un Rol existente")
    public void updateRol(){

        RolDTO rolNuevoDTO = new RolDTO();
        rolNuevoDTO.setDescripcion("SUPER ADMIN");

        Mockito.when(rolRepository.existsById(3L)).thenReturn(true);
        Mockito.when(rolRepository.findById(3L)).thenReturn(Optional.of(this.rol3));

        Mockito.doAnswer(invocation -> {
            RolDTO source = invocation.getArgument(0);
            Rol target = invocation.getArgument(1);
            target.setDescripcion(source.getDescripcion());
            return null;
        }).when(mapper).map(rolNuevoDTO,this.rol3);

        Rol rolActualizado = Rol.builder()
                .id(3L)
                .descripcion("SUPER ADMIN")
                .build();
        Mockito.when(rolRepository.save(this.rol3)).thenReturn(rolActualizado);

        Rol resultado = rolService.update(3L, rolNuevoDTO);
        assertNotNull(resultado);
        assertEquals("SUPER ADMIN", resultado.getDescripcion());

        Mockito.verify(rolRepository, Mockito.times(1)).existsById(3L);
        Mockito.verify(rolRepository, Mockito.times(1)).findById(3L);
        Mockito.verify(mapper, Mockito.times(1)).map(rolNuevoDTO, this.rol3);
        Mockito.verify(rolRepository, Mockito.times(1)).save(this.rol3);
    }


}
