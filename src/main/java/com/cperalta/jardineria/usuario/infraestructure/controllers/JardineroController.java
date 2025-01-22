package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.JardineroService;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<Jardinero>> getByAll(){
        return new ResponseEntity<>(jardineroService.getAll(), HttpStatus.OK);
    }


}
