package com.cperalta.jardineria.usuario.infraestructure.controllers;


import com.cperalta.jardineria.usuario.application.services.PersonaService;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.infraestructure.config.JwtUtil;
import com.cperalta.jardineria.usuario.infraestructure.dto.LoginDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

//@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Transactional
public class LoginControllerUnitTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PersonaService personaService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginController loginController;

    @Test
    @DisplayName("Test de integración para retornar el token con la información del login cuando es un inicio de sesión correcto")
    void testRetornarTokenEinformacionDelUsuarioCorrectamente() {
        // Creo el request del controlador del Login
        String email = "test@example.com";
        String password = "password123";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setContrasenia(password);

        // Creo una persona la cual se utilizará como respuesta para la busqueda por email
        Persona persona = new Persona(1, email, "Test", "User", password, "tokendeactivacionrandom",
                new Role(1L,"ADMIN"), new Estado(1L, "ACTIVO"));

        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(email, password);

        // Mocks
        Mockito.when(personaService.findByEmail(email)).thenReturn(Optional.of(persona));
        Mockito.when(authenticationManager.authenticate(loginToken)).thenReturn(Mockito.mock(Authentication.class));
        Mockito.when(jwtUtil.generateToken(email)).thenReturn("mocked-jwt-token");


        ResponseEntity<Map<String, Object>> response = loginController.login(loginDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals("mocked-jwt-token", body.get("token"));
        Assertions.assertEquals("Test", body.get("nombre"));
        Assertions.assertEquals(email, body.get("email"));

        Mockito.verify(personaService).findByEmail(email);
        Mockito.verify(authenticationManager).authenticate(loginToken);
        Mockito.verify(jwtUtil).generateToken(email);
    }

    @Test
    @DisplayName("Test de integración del login cuando se ingresan un email de un usuario que no existe")
    void testParaLoginConCredencialesIncorrectas() {
        String email = "test@example.com";
        String password = "pass123";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setContrasenia(password);

        Mockito.when(personaService.findByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = loginController.login(loginDTO);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertEquals("Credenciales Incorrectas", response.getBody().get("error"));

        Mockito.verify(personaService).findByEmail(email);
        Mockito.verifyNoInteractions(authenticationManager);
        Mockito.verifyNoInteractions(jwtUtil);
    }

    @Test
    @DisplayName("Test de integración del login cuando se ingresa una contraseña incorrecta")
    void testLoginContraseniaIncorrecta() {
        // seteo valores y acciones
        String email = "pepe@gmail.com";
        String password = "contraseniaincorrecta";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setContrasenia(password);

        Persona persona = new Persona(1, email, "Pepe", "Becerra", "12345","tokendeactivacionrandom",
                new Role(1L,"USER"), new Estado(1L, "ACTIVE"));

        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(email, password);

        Mockito.when(personaService.findByEmail(email)).thenReturn(Optional.of(persona));
        Mockito.when(authenticationManager.authenticate(loginToken)).thenThrow(BadCredentialsException.class);

        // Llamo al login del controller
        ResponseEntity<Map<String, Object>> response = loginController.login(loginDTO);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertEquals("Credenciales incorrectas", response.getBody().get("error"));

        // Verifico las interacciones
        Mockito.verify(personaService).findByEmail(email);
        Mockito.verify(authenticationManager).authenticate(loginToken);
        Mockito.verifyNoInteractions(jwtUtil);
    }
}
