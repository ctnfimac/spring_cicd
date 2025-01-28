package com.cperalta.jardineria.usuario.infraestructure.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private Algorithm ALGORITHM;

    public String generateToken(String email){
        ALGORITHM =Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(email)
                .withIssuer("ServiciosDeJardineria")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(600))) // expira en 15 dias a partir de la fecha actual
                .sign(ALGORITHM);
    }

    // verifico si el token es valido
    public boolean isTokenValid(String jwt){
        try{
            Algorithm ALGORITHM =Algorithm.HMAC256(secretKey);
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException e){
            System.out.println("Error en isTokenValid: " + e);
            return false;
        }
    }


    public String getEmail(String jwt){
        ALGORITHM = Algorithm.HMAC256(secretKey);
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();// por que en el withSubject le di el username en el metodo create
    }

    public String encryptPassword(String plainPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(plainPassword);
    }
}
