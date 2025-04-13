package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.RegisterClientService;
import com.cperalta.jardineria.usuario.application.services.RegisterGardenerService;
import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrocliente.RegisterClientRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrocliente.RegisterClientResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegisterGardenerRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegisterGardenerResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClientMapper;
import com.cperalta.jardineria.usuario.infraestructure.mapper.GardenerMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
@AllArgsConstructor
public class RegisterUserController {

    private final RegisterGardenerService registerGardenerService;
    private final RegisterClientService registerClientService;
    private final GardenerMapper jardineroMapper;
    private final ClientMapper clienteMapper;

    @PostMapping("/gardener")
    public ResponseEntity<RegisterGardenerResponseDTO> registrarJardinero(@Validated @RequestBody RegisterGardenerRequestDTO registroJardineroRequestDTO){
        GardenerRecord jardineroRecord = jardineroMapper.registerGardenerRequestDTOtoGardenerRecord(registroJardineroRequestDTO);
        Gardener jardineroNuevo = registerGardenerService.register(jardineroRecord);
        RegisterGardenerResponseDTO registroJardineroResponseDTO = jardineroMapper.gardenerToRegisterGardenerResponseDTO(jardineroNuevo);
        return new ResponseEntity<>(registroJardineroResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public ResponseEntity<RegisterClientResponseDTO> registrarCliente(@Validated @RequestBody RegisterClientRequestDTO registroClienteRequestDTO){
        ClientRecord clienteRecord = clienteMapper.registerClientRequestDTOtoClientRecord(registroClienteRequestDTO);
        Client clienteNuevo = registerClientService.register(clienteRecord);
        RegisterClientResponseDTO registroClienteResponseDTO = clienteMapper.clientToRegisterClientResponseDTO(clienteNuevo);
        return new ResponseEntity<>(registroClienteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/activate_gardener")
    public ResponseEntity<Map<String, Boolean>> confirmarRegistroDelJardinero(@RequestParam("token") String token, @RequestParam("id") String id){
        Boolean resultado = registerGardenerService.activate(id, token);

        Map<String, Boolean> response = new HashMap<>();
        response.put("usuario_activado", resultado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/activate_client")
    public ResponseEntity<Map<String, Boolean>> confirmarRegistroDelCliente(@RequestParam("token") String token, @RequestParam("id") String id){
        Boolean resultado = registerClientService.activate(id, token);

        Map<String, Boolean> response = new HashMap<>();
        response.put("usuario_activado", resultado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
