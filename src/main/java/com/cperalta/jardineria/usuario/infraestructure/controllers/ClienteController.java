package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.ClienteService;
import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "API de Clientes", description = "CRUD de los Clientes del Sistema")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Cliente por ID",
            description = "Este endpoint retorna un Cliente específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long id){
        return clienteService.getById(id)
                .map( cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    @Operation(
            summary = "Obtener todos los Clientes",
            description = "Este endpoint retorna todos los Clientes existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Cliente>> getAll(){
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }


    @PostMapping
    @Operation(
            summary = "Crear un Cliente",
            description = "Este endpoint es para crear un nuevo Cliente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ClienteResponseDTO> create(@Validated @RequestBody ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.clienteRequestDTOtoCliente(clienteRequestDTO);
        Cliente clienteCreado = clienteService.create(cliente);
        ClienteResponseDTO clienteResponseDTO = clienteMapper.clienteToClienteResponseDTO(clienteCreado);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Cliente",
            description = "Este endpoint es para eliminar un Cliente existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return clienteService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Cliente existente",
            description = "Este endpoint es para Actualizar un Cliente existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable("id") Long id,
                                                     @RequestBody ClienteRequestUpdateDTO clienteRequestUpdateDTO){
        Cliente cliente = clienteMapper.clienteRequestUpdateDTOtoCliente(clienteRequestUpdateDTO);
        Cliente clienteActualizado = clienteService.update(id,cliente);
        ClienteResponseDTO clienteResponseDTO= clienteMapper.clienteToClienteResponseDTO(clienteActualizado);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.OK);
    }
}
