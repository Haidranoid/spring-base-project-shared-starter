package com.springbaseproject.sharedstarter.services;

import com.springbaseproject.sharedstarter.properties.ApplicationProperties;
import com.springbaseproject.sharedstarter.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtService {
    private final JwtProperties jwtProperties;
    private final ApplicationProperties applicationProperties;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final SignatureAlgorithm signatureAlgorithm;

    public JwtService(JwtProperties jwtProperties, ApplicationProperties applicationProperties, PrivateKey privateKey, PublicKey publicKey, SignatureAlgorithm signatureAlgorithm) {
        this.jwtProperties = jwtProperties;
        this.applicationProperties = applicationProperties;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    // ================================= GENERATE TOKEN =================================
    private String generateToken(String subject, Map<String, Object> extraClaims, long expirationMillis) {
        if (privateKey == null) {
            throw new IllegalStateException("This service cannot sign tokens (no private key configured).");
        }

        return Jwts.builder()
                .signWith(privateKey, signatureAlgorithm)
                .header().add("typ","JWT").and()
                .issuer(jwtProperties.issuer())
                .subject(subject)
                .claims(extraClaims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMillis))
                .audience().add(jwtProperties.audience()).and()
                .compact();
    }

    // ================================= DECODE TOKEN =================================
    public Claims decode(String token) {
        if (publicKey == null) {
            throw new IllegalStateException("No public key configured for JWT validation.");
        }

        return Jwts.parser()
                .verifyWith(publicKey)
                .requireIssuer(jwtProperties.issuer())
                .requireAudience(applicationProperties.name())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ================================= TOKEN HELPERS ========================================
    public String generateAccessToken(String subject) {
        return generateToken(subject, new HashMap<>(), jwtProperties.accessTokenExpiration());
    }

    public String generateAccessToken(String subject, List<String> roles, List<String> scopes) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("scope", scopes);

        return generateToken(subject, claims, jwtProperties.accessTokenExpiration());
    }

    public String generateAccessToken(String subject, Map<String, Object> extraClaims) {
        return generateToken(subject, extraClaims, jwtProperties.accessTokenExpiration());
    }

    public String generateRefreshToken(String subject) {
        return generateToken(subject, new HashMap<>(), jwtProperties.refreshTokenExpiration());
    }

    public String generateRefreshToken(String subject, Map<String, Object> extraClaims) {
        return generateToken(subject, extraClaims, jwtProperties.refreshTokenExpiration());
    }

    // =================================== TOKEN UTILS ========================================
    public Boolean isExpired(String token) {
        return decode(token).getExpiration().before(new Date());
    }
}
