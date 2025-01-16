package com.cperalta.jardineria.servicios.infraestructure.controllers;

import com.cperalta.jardineria.servicios.application.services.ServicioService;
import com.cperalta.jardineria.servicios.infraestructure.dto.ServicioResponseDTO;
import com.cperalta.jardineria.servicios.infraestructure.mapper.ServicioMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicio")
@AllArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> getServicioById(@PathVariable("id") UUID id){
        return servicioService.getById(id)
                .map(servicio -> ResponseEntity.ok(servicioMapper.servicioToServicioResponseDTO(servicio)))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDTO>> getAllServicios(){
        List<ServicioResponseDTO> serviciosResponseDTO = servicioService.getAll().stream()
                .map(servicioMapper::servicioToServicioResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviciosResponseDTO, HttpStatus.OK);
    }


}
