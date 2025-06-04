package com.bank.bankservice.service.contract;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

public interface JwtService {
    boolean isTokenExpired(String token);

    Claims extractAllClaims(String token);

    <T> T extractClaim(String token, Function<Claims, T> resolver);

    Long extractId(String token);

    String extractUsername(String token);

    Date extractExpiration(String token);

    public SecretKey getSigningKey(); 
}
