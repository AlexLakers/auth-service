package com.alex.bank.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

//Optional instead of manual
@Component
@Slf4j
public class JwtSecretGenerator {

    public String generateSafeSecret() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256 bits for HS256
        random.nextBytes(keyBytes);
        return Encoders.BASE64.
                encode(keyBytes);
    }

    public void validateSecret(String secret) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            if (keyBytes.length < 32) {
                throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 bytes)");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT secret format", e);
        }
    }
}