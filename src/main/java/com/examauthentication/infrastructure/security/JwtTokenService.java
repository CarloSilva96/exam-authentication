package com.examauthentication.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.examauthentication.infrastructure.exception.AuthException;
import com.examauthentication.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {
    private static final String OFF_SET_ID = "-03:00";

    @Value("${security.jwt.token.expiration_minutes}")
    private Long EXPIRATION_TIME_MINUTES;

    @Value("${security.jwt.token.secret}")
    private String JWT_SECRET;

    public String generationJwtToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer("Exam-Auth-CJ")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName())
                    .withClaim("cpf", user.getCpf())
                    .withExpiresAt(expirationJwtToken())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new AuthException("Error generating token: ", exception);
        }
    }

    private Instant expirationJwtToken() {
        return LocalDateTime.now().plusMinutes(EXPIRATION_TIME_MINUTES).toInstant(ZoneOffset.of(OFF_SET_ID));
    }


    public String getUserLogin(String jwtToken) {
        try {
            var algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                    .withIssuer("Exam-Auth-CJ")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }

    }

}
