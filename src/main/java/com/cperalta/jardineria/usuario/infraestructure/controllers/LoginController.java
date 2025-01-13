package com.cperalta.jardineria.usuario.infraestructure.controllers;


import com.cperalta.jardineria.usuario.application.services.PersonaService;
import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.infraestructure.config.JwtUtil;
import com.cperalta.jardineria.usuario.infraestructure.dto.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class LoginController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final PersonaService personaService;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDTO loginDTO){
        Optional<Persona> personaBuscada = personaService.findByEmail(loginDTO.getEmail());

        if (personaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

        // Genero un token de autenticación
        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getContrasenia()
        );

        try{
            Persona persona = personaBuscada.get();
            Authentication authentication = authenticationManager.authenticate(loginToken);
            String jwt = jwtUtil.generateToken(loginDTO.getEmail());

            // Construyo la respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("nombre", persona.getNombre());
            response.put("email", persona.getEmail());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

    }
}
