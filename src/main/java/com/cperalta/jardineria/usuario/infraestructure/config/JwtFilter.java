package com.cperalta.jardineria.usuario.infraestructure.config;

import com.cperalta.jardineria.usuario.application.services.PersonaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    /*Esta clase es utilizada para verificar si la peticion necesita las validaciones o no*/

    private final JwtUtil jwtUtil;
    private final PersonaService personaService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1. Validar que sea un Header Authorization valido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){
            // acá ingrasa cuando se hace el login ya que no se envia ningun token
            filterChain.doFilter(request, response);
            return;
        }

        //2. Validar que el JWT sea valido
        String jwt = authHeader.split(" ")[1].trim(); //separo el texto Bearer y el token que viene desde postman
        if(!jwtUtil.isTokenValid(jwt)){
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Cargar el usuario del UserDetailService
        String username = jwtUtil.getEmail(jwt);
        // busco dentro del repositorio de usuarios
        User user = (User) userDetailsService.loadUserByUsername(username);
        // 4. Cargar al usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );

        // agrego detalles al contexto de seguridad, puede no estar
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
