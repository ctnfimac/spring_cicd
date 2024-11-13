package com.todotic.ContactListApi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwUtil jwUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwUtil jwUtil, UserDetailsService userDetailsService) {
        this.jwUtil = jwUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. Validar que sea un Header Authorization valido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        // el Bearer se ve desde postman cuando se manda una solicitud con token
        if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){
            System.out.println("ENTRO A ESTE VALIDADOR");
            // peticion que no se resuelve en el contexto de seguridad, petición inválida
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Validar que el JWT sea valido
        System.out.println("authHeader:" + authHeader);
        String jwt = authHeader.split(" ")[1].trim(); //separo el texto Bearer y el token que viene desde postman
        System.out.println("JWT:" + jwt);
        if(!this.jwUtil.isValid(jwt)){
            filterChain.doFilter(request, response);
            System.out.println("Entro acá");
            return;
        }
        System.out.println("PAso 2 ok");
        // 3. Cargar el usuario del UserDetailService
        String username = this.jwUtil.getUsername(jwt);
        System.out.println("username: " + username);
          // busco dentro del repositorio de usuarios
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        // 4. Cargar al usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );

        // agrego detalles al contexto de seguridad, puede no estar
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken: " + authenticationToken);
        filterChain.doFilter(request, response); // antes que siga con los filtros cargué el contexto
    }
}
