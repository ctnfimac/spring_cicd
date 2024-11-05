package com.todotic.ContactListApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // para los CORS
                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            //.requestMatchers(HttpMethod.GET, "/api/*").permitAll() // todo lo que llega por get /api/ lo permite
                            //.requestMatchers(HttpMethod.PUT).denyAll()
                            .requestMatchers(HttpMethod.POST, "/api/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/*").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.PUT, "/api/*").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated(); //.permitAll()

                })
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

   /* ya lo hice por base de datos en UserSecurityService

   @Bean
    public UserDetailsService memoryUsers(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); // No utiliza encriptación (solo para pruebas)
    }
}
