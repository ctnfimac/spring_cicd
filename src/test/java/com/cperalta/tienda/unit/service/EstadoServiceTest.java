package com.cperalta.tienda.unit.service;

import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.RolRepository;
import com.cperalta.tienda.service.EstadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EstadoServiceTest {

    @InjectMocks
    private EstadoServiceImpl estadoService;

    @Mock
    private EstadoRepository estadoRepository;

    @Mock
    private ModelMapper mapper;

    private Estado estado1;
    private Estado estado2;
    private List<Estado> listaDeEstados;

    @BeforeEach
    void setUp() {
        this.estado1 = Estado.builder()
                .descripcion("ACTIVO")
                .build();

        this.estado2 = Estado.builder()
                .descripcion("BLOQUEADO")
                .build();

        this.listaDeEstados = Arrays.asList(this.estado1, this.estado2);
    }

    @Test
    @DisplayName("Obtengo todos los estados")
    public void testObtengoTodosLosEstados() {
        Mockito.when(estadoRepository.findAll()).thenReturn(this.listaDeEstados);
        List<Estado> estados = estadoService.getAll();
        assertEquals(estados.size(), listaDeEstados.size());
    }

    @Test
    @DisplayName("Obtengo un Estado buscado por ID correctamente")
    public void testBuscoUnEstadoPorIdCorrectamente() {
        Mockito.when(estadoRepository.findById(2L)).thenReturn(Optional.of(this.estado2));
        Estado estado = estadoService.getById(2L);
        assertEquals(estado, this.estado2);
        assertEquals("BLOQUEADO", estado.getDescripcion());
    }

    @Test
    @DisplayName("Obtengo un Estado buscado por ID incorrectamente")
    public void testBuscoUnEstadoPorIdIncorrectamente() {
        Mockito.when(estadoRepository.findById(3L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> estadoService.getById(3L));
        assertEquals("El Estado con el ID 3 no existe", exception.getMessage());
        Mockito.verify(estadoRepository, Mockito.times(1)).findById(3L);
    }

    @Test
    @DisplayName("Elimino un Estado por el ID Correctamente")
    public void testEliminoEstadoPorIdCorrectamente() {
        Mockito.when(estadoRepository.existsById(1L)).thenReturn(true);
        boolean resultado = estadoService.delete(1L);

        assertTrue(resultado);

        // verifico si los métodos del service fueron llamados
        Mockito.verify(estadoRepository, Mockito.times(1)).existsById(1L);
        Mockito.verify(estadoRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Elimino un Estado por el ID Incorrectamente")
    public void testEliminoEstadoPorIdIncorrectamente() {
        Mockito.when(estadoRepository.existsById(5L)).thenReturn(false);
        boolean resultado = estadoService.delete(5L);

        assertFalse(resultado);

        // verifico si los métodos del service fueron llamados
        Mockito.verify(estadoRepository, Mockito.times(1)).existsById(5L);
        Mockito.verify(estadoRepository, Mockito.times(0)).deleteById(5L);
    }


    @Test
    @DisplayName("Guardo correctamente un nuevo Estado")
    public void testGuardoUnEstadoNuevoCorrectamente() {
        EstadoDTO estadoNuevoDTO = new EstadoDTO();
        estadoNuevoDTO.setDescripcion("BLOQUEADO");

        Estado estadoNuevoEntity = new Estado();
        estadoNuevoEntity.setDescripcion("BLOQUEADO");

        Estado estadoNuevoResultado = new Estado();
        estadoNuevoResultado.setId(3);
        estadoNuevoResultado.setDescripcion("BLOQUEADO");

        List<Estado> estados = new ArrayList<>(Arrays.asList(this.estado1, this.estado2));

        Mockito.when(mapper.map(estadoNuevoDTO, Estado.class)).thenReturn(estadoNuevoEntity);
        //Mockito.when(rolRepository.save(rolNuevoEntity)).thenReturn(rolNuevoResultado);
        Mockito.when(estadoRepository.save(estadoNuevoEntity)).thenAnswer( invocation -> {
            Estado nuevo = invocation.getArgument(0);
            nuevo.setId(3);
            estados.add(nuevo);
            return nuevo;
        });
        Mockito.when(estadoRepository.findAll()).thenReturn(estados);

        Estado resultado = estadoService.create(estadoNuevoDTO);

        assertNotNull(resultado);
        assertEquals(3, resultado.getId());
        assertEquals("BLOQUEADO", resultado.getDescripcion());

        // verifico la cantidad nueva de roles
        List<Estado> listaActualizada = estadoService.getAll();
        assertEquals(3, listaActualizada.size());

        // verifico que se ejecutaron las interacciones de los mocks
        Mockito.verify(mapper, Mockito.times(1)).map(estadoNuevoDTO, Estado.class);
        Mockito.verify(estadoRepository, Mockito.times(1)).save(estadoNuevoEntity);
    }


    @Test
    @DisplayName("Modifico los valores de un Estado existente")
    public void testModificoEstado(){

        EstadoDTO estadoNuevoDTO = new EstadoDTO();
        estadoNuevoDTO.setDescripcion("SUSPENDIDO");

        Mockito.when(estadoRepository.findById(2L)).thenReturn(Optional.of(this.estado2));

        Mockito.doAnswer(invocation -> {
            EstadoDTO source = invocation.getArgument(0);
            Estado target = invocation.getArgument(1);
            target.setDescripcion(source.getDescripcion());
            return null;
        }).when(mapper).map(estadoNuevoDTO,this.estado2);

        Estado estadoActualizado = Estado.builder()
                .id(2)
                .descripcion("SUSPENDIDO")
                .build();
        Mockito.when(estadoRepository.save(this.estado2)).thenReturn(estadoActualizado);

        Estado resultado = estadoService.update(2L, estadoNuevoDTO);
        assertNotNull(resultado);
        assertEquals("SUSPENDIDO", resultado.getDescripcion());

        Mockito.verify(estadoRepository, Mockito.times(1)).findById(2L);
        Mockito.verify(mapper, Mockito.times(1)).map(estadoNuevoDTO, this.estado2);
        Mockito.verify(estadoRepository, Mockito.times(1)).save(this.estado2);
    }
}
