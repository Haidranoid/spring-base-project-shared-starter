package com.springbaseproject.sharedstarter.autoconfiguration;

import com.springbaseproject.sharedstarter.properties.ApplicationProperties;
import com.springbaseproject.sharedstarter.properties.JwtProperties;
import com.springbaseproject.sharedstarter.security.JwtAuthoritiesConverter;
import com.springbaseproject.sharedstarter.services.JwtService;
import com.springbaseproject.sharedstarter.utils.SecurityUtils;
import io.jsonwebtoken.security.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class, ApplicationProperties.class})
public class SecurityAutoConfig {

    private final JwtProperties jwtProperties;
    private final ApplicationProperties appProperties;
    private final SignatureAlgorithm signatureAlgorithm;
    @Nullable
    private final PrivateKey privateKey;
    @Nullable
    private final PublicKey publicKey;

    @Bean
    public JwtService jwtService() {
        return new JwtService(jwtProperties, appProperties, privateKey, publicKey, signatureAlgorithm);
    }

    @Bean
    public JwtDecoder jwtDecoder(@Nullable PublicKey publicKey) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) publicKey).build();
    }

    @Bean
    public JwtAuthoritiesConverter jwtAuthoritiesConverter(JwtService service) {
        return new JwtAuthoritiesConverter(service);
    }

    @Bean
    public SecurityUtils securityUtils() {
        return new SecurityUtils();
    }
}
