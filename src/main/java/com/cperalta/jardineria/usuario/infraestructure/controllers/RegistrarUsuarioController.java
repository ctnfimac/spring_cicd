package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.RegistrarJardineroService;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/registrar")
@AllArgsConstructor
public class RegistrarUsuarioController {

    private final RegistrarJardineroService registrarJardineroService;
    private final JardineroMapper jardineroMapper;

    @PostMapping("/jardinero")
    public ResponseEntity<RegistroJardineroResponseDTO> registrarJardinero(@Validated @RequestBody RegistroJardineroRequestDTO registroJardineroRequestDTO){
        JardineroRecord jardineroRecord = jardineroMapper.registroJardineroRequestDTOtoJardineroRecord(registroJardineroRequestDTO);
        Jardinero jardineroNuevo = registrarJardineroService.registrar(jardineroRecord);
        RegistroJardineroResponseDTO registroJardineroResponseDTO = jardineroMapper.jardineroToRegistroJardineroResponseDTO(jardineroNuevo);
        return new ResponseEntity<>(registroJardineroResponseDTO, HttpStatus.CREATED);
    }

}
