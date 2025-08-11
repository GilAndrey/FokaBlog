package com.github.fokaBlog.security;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import java.util.Date;

// Generation and token validation JWT
@Component
public class JWTUtils {

    // É possivel mover para application.properties, secret key
    private final String SECRET_KEY = "my-secret-key";
    private final long EXPIRATION_TIME = (long) (1000 * 60 * 60 * 10); // -> 10 hours

    // Token generation above the Email
    private String generateToken(String username) {
        JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return username;
    }

    // Extract the username (email) token jwt
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }

    // Validation to the correct user
    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token));
    }

}
