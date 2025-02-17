package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.RegistrarJardineroService;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.adapters.EncryptionAdapter;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/registro")
@AllArgsConstructor
public class RegistrarUsuarioController {

    private final RegistrarJardineroService registrarJardineroService;
    private final JardineroMapper jardineroMapper;
    private final EncryptionAdapter encryptionAdapter;

    @PostMapping("/jardinero")
    public ResponseEntity<RegistroJardineroResponseDTO> registrarJardinero(@Validated @RequestBody RegistroJardineroRequestDTO registroJardineroRequestDTO){
        JardineroRecord jardineroRecord = jardineroMapper.registroJardineroRequestDTOtoJardineroRecord(registroJardineroRequestDTO);
        Jardinero jardineroNuevo = registrarJardineroService.registrar(jardineroRecord);
        RegistroJardineroResponseDTO registroJardineroResponseDTO = jardineroMapper.jardineroToRegistroJardineroResponseDTO(jardineroNuevo);
        return new ResponseEntity<>(registroJardineroResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/activar")
    public ResponseEntity<Map<String, Boolean>> confirmarRegistro(@RequestParam("token") String token, @RequestParam("id") String id){
        //System.out.println("TOKEN: " + token);
        //System.out.println("EMAIL:" + encryptionAdapter.decrypt(id));
        //return ResponseEntity.ok("Token: " + token + ", ID: " + encryptionAdapter.decrypt(id));
        Boolean resultado = registrarJardineroService.activar(id, token);

        Map<String, Boolean> response = new HashMap<>();
        response.put("usuario_activado", resultado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
