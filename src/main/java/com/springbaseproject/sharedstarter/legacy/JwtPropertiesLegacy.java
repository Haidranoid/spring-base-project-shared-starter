package com.springbaseproject.sharedstarter.legacy;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtPropertiesLegacy {
    private String algorithm;              // ES256, PS256, etc.
    private String privateKey;             // PEM o Base64 (Vault / Keystore)
    private String publicKey;              // PEM o Base64
    private long accessTokenExpiration;    // ms
    private long refreshTokenExpiration;   // ms
    private String issuer;
    private List<String> audience;
}
