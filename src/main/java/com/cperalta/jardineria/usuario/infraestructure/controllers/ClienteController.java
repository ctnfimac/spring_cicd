package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.ClienteService;
import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long id){
        return clienteService.getById(id)
                .map( cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@Validated @RequestBody ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.clienteRequestDTOtoCliente(clienteRequestDTO);
        Cliente clienteCreado = clienteService.create(cliente);
        ClienteResponseDTO clienteResponseDTO = clienteMapper.clienteToClienteResponseDTO(clienteCreado);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return clienteService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
