package com.springbaseproject.sharedstarter.autoconfiguration;

import com.springbaseproject.sharedstarter.properties.JwtProperties;
import com.springbaseproject.sharedstarter.security.JwtAuthenticationConverter;
import com.springbaseproject.sharedstarter.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class})
public class SecurityAutoConfig {

    private final JwtProperties jwtProperties;

    @Bean
    public JwtDecoder jwtDecoder(@Nullable PublicKey publicKey) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey((RSAPublicKey) publicKey).build();
        OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(jwtProperties.issuer());

        decoder.setJwtValidator(validator);

        return decoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public SessionUtils sessionUtils() {
        return new SessionUtils();
    }
}
