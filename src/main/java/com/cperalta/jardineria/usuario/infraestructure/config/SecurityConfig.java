package com.cperalta.jardineria.usuario.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // para los CORS
                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .requestMatchers("/api/estado/**").hasRole("ADMIN")
                            .requestMatchers("/api/rol/**").hasRole("ADMIN")
                            .requestMatchers("/api/tipodeservicio/**").hasRole("ADMIN")
                            .requestMatchers("/api/servicio/**").hasRole("ADMIN")
                            .requestMatchers("/api/estadodecontratacion/**").hasRole("ADMIN")
                            .requestMatchers("/api/contrata/**").hasRole("ADMIN")
                            .requestMatchers("/api/trabajorealizado/**").hasRole("ADMIN")
                            .requestMatchers("/api/cliente/**").hasRole("ADMIN")
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
