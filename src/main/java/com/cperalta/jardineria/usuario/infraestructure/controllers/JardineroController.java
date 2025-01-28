package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.JardineroService;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jardinero")
@AllArgsConstructor
public class JardineroController {

    private final JardineroService jardineroService;
    private final JardineroMapper jardineroMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Jardinero> getById(@PathVariable("id") Long id){
        return jardineroService.getById(id)
                .map( jardineroCreado -> ResponseEntity.ok(jardineroCreado))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Jardinero>> getAll(){
        return new ResponseEntity<>(jardineroService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return jardineroService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JardineroResponseDTO> create(@Validated @RequestBody JardineroRequestDTO jardineroRequestDTO){
        Jardinero jardinero = jardineroMapper.jardineroRequestDTOtoJardinero(jardineroRequestDTO);
        Jardinero jardineroCreado = jardineroService.create(jardinero);
        JardineroResponseDTO jardineroResponseDTO = jardineroMapper.jardineroToJardineroResponseDTO(jardineroCreado);
        return new ResponseEntity<>(jardineroResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JardineroResponseDTO> update(@PathVariable("id") Long id,
                                                       @RequestBody JardineroRequestUpdateDTO jardineroRequestUpdateDTO){
        Jardinero jardinero = jardineroMapper.jardineroRequestUpdateDTOtoJardinero(jardineroRequestUpdateDTO);
        Jardinero jardineroActualizado = jardineroService.update(id, jardinero);
        JardineroResponseDTO jardineroResponseDTO = jardineroMapper.jardineroToJardineroResponseDTO(jardineroActualizado);
        return new ResponseEntity<>(jardineroResponseDTO, HttpStatus.OK);
    }

}
