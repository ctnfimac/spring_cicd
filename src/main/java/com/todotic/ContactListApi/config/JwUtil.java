package com.todotic.ContactListApi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwUtil {
    private static String SECRET_KEY =  "Christi4n-ped1do";
    private static Algorithm ALGORITHM =Algorithm.HMAC256(SECRET_KEY);

    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("Christian-pedido")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15))) // expira en 15 dias a partir de la fecha actual
                .sign(ALGORITHM);
    }


    // verifico si el token es valido
    public boolean isValid(String jwt){
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException  e){
            System.out.println("Error en isValid: " + e);
            return false;
        }
    }

    public String getUsername(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();// por que en el withSubject le di el username en el metodo create
    }
}
