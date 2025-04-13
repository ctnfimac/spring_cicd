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
import org.springframework.web.cors.CorsConfiguration;

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
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())) // para los CORS
                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .requestMatchers("/api/v1/status/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/role/**").hasRole("ADMIN")
                            .requestMatchers("/api/tipodeservicio/**").hasRole("ADMIN")
                            .requestMatchers("/api/servicio/**").hasRole("ADMIN")
                            .requestMatchers("/api/estadodecontratacion/**").hasRole("ADMIN")
                            .requestMatchers("/api/contrata/**").hasRole("ADMIN")
                            .requestMatchers("/api/trabajorealizado/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/client/**").hasRole("ADMIN")
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/register/gardener").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/register/client").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/register/activate_client").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/register/activate_gardener").permitAll()
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
