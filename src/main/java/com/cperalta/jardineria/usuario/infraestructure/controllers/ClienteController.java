package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.ClientService;
import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClientRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClientRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClientResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@Tag(name = "API de Clientes", description = "CRUD de los Clientes del Sistema")
@AllArgsConstructor
public class ClienteController {
    private final ClientService clientService;
    private final ClientMapper clienteMapper;

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Client por ID",
            description = "Este endpoint retorna un Client específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Client> getById(@PathVariable("id") Long id){
        return clientService.getById(id)
                .map( cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    @Operation(
            summary = "Obtener todos los Clientes",
            description = "Este endpoint retorna todos los Clientes existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Client>> getAll(){
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }


    @PostMapping
    @Operation(
            summary = "Crear un Client",
            description = "Este endpoint es para crear un nuevo Client. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ClientResponseDTO> create(@Validated @RequestBody ClientRequestDTO clientRequestDTO){
        Client cliente = clienteMapper.clientRequestDTOtoClient(clientRequestDTO);
        Client clienteCreated = clientService.create(cliente);
        ClientResponseDTO clienteResponseDTO = clienteMapper.clientToClientResponseDTO(clienteCreated);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Client",
            description = "Este endpoint es para eliminar un Client existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return clientService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Client existente",
            description = "Este endpoint es para Actualizar un Client existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ClientResponseDTO> update(@PathVariable("id") Long id,
                                                     @RequestBody ClientRequestUpdateDTO clienteRequestUpdateDTO){
        Client cliente = clienteMapper.clientRequestUpdateDTOtoClient(clienteRequestUpdateDTO);
        Client clienteActualizado = clientService.update(id,cliente);
        ClientResponseDTO clienteResponseDTO= clienteMapper.clientToClientResponseDTO(clienteActualizado);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.OK);
    }
}
