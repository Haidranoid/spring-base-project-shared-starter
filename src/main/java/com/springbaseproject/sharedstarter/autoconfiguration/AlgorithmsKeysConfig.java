package com.springbaseproject.sharedstarter.autoconfiguration;

import com.springbaseproject.sharedstarter.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@AutoConfiguration
@RequiredArgsConstructor
public class AlgorithmsKeysConfig {

    private final JwtProperties jwtProperties;

    // Key factory ==========================================
    @Bean
    public KeyFactory keyFactory() throws Exception {
        String algorithm = jwtProperties.algorithm();

        if (algorithm == null) {
            throw new IllegalArgumentException("JWT algorithm must be configured (e.g. ES256, PS256)");
        }

        return switch (algorithm) {
            case "ES256", "ES384" -> KeyFactory.getInstance("EC");
            case "PS256", "PS384", "RS256" -> KeyFactory.getInstance("RSA");
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        };
    }

    @Bean
    public SignatureAlgorithm resolveAlgorithm() {
        String algorithm = jwtProperties.algorithm();

        return switch (algorithm) {
            case "ES256" -> Jwts.SIG.ES256;
            case "ES384" -> Jwts.SIG.ES384;
            case "PS256" -> Jwts.SIG.PS256;
            case "PS384" -> Jwts.SIG.PS384;
            case "RS256" -> Jwts.SIG.RS256; // legacy
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        };
    }

    @Bean
    @ConditionalOnProperty(name = "application.security.jwt.private-key")
    public PrivateKey privateKey(KeyFactory keyFactory) throws Exception {
        byte[] privateKeyDecoded = decodeKey(jwtProperties.privateKey());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyDecoded);

        return keyFactory.generatePrivate(privateKeySpec);
    }

    @Bean
    @ConditionalOnProperty(name = "application.security.jwt.public-key")
    public PublicKey publicKey(KeyFactory keyFactory) throws Exception {
        byte[] publicKeyDecoded = decodeKey(jwtProperties.publicKey());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyDecoded);

        return keyFactory.generatePublic(publicKeySpec);
    }

    // PEM/Base64
    private byte[] decodeKey(String key) {
        String clean = key
                .replaceAll("-----BEGIN(.*?)-----", "")
                .replaceAll("-----END(.*?)-----", "")
                .replaceAll("\\s", "");
        return Base64.getDecoder().decode(clean);
    }

    /*
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    */
}

