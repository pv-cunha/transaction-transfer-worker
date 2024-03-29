package com.transactiontransferworker.business.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.transactiontransferworker.repository.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtBS {
    @Value("${jwt.security.secret}")
    private String secret;

    @Value("${jwt.security.expiration}")
    private Long jwtExpiration;

    public String generateToken(User user) {

        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuer("transaction-transfer-worker")
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(getJwtAlgorithm());

    }

    public String decodeToken(String token) {

        return JWT.require(getJwtAlgorithm())
                .withIssuer("transaction-transfer-worker")
                .build()
                .verify(token)
                .getSubject();
    }

    private Algorithm getJwtAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
