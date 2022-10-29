package com.demo_project.rest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${secret-key}")
    private String secretKey;

    public Map<String,String> generateToken(String username,String role){

        Date accessTokenexpireDate = Date.from(ZonedDateTime.now().plusDays(1).toInstant());
        Date refreshTokenexpireDate = Date.from(ZonedDateTime.now().plusDays(10).toInstant());


        Algorithm algorithm = Algorithm.HMAC256(secretKey);


        String accessToken = JWT.create()
                .withSubject(username)
                .withClaim("role",role)
                .withIssuer("gilalgilally")
                .withIssuedAt(new Date())
                .withExpiresAt(accessTokenexpireDate)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(username)
                .withClaim("role",role)
                .withIssuer("gilalgilally")
                .withIssuedAt(new Date())
                .withExpiresAt(refreshTokenexpireDate)
                .sign(algorithm);

        return Map.of("access-token", accessToken,
                     "refresh-token", refreshToken);
    }

    public DecodedJWT validateToken(String accessToken){


            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(accessToken);


        return decodedJWT;
    }



}
