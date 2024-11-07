package com.todotic.ContactListApi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
}
