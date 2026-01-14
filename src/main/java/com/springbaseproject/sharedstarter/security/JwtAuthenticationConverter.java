package com.springbaseproject.sharedstarter.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>{

    private final JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // OAuth2-compatible scopes
        authorities.addAll(scopesConverter.convert(jwt));

        // Roles
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles != null) {
            authorities.addAll(
                    roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList()
            );
        }

        // Authorities
        /*
        List<String> scopes = jwt.getClaimAsStringList("scope");
        if (scopes != null) {
            authorities.addAll(
                    scopes.stream()
                            //.map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                            .map(SimpleGrantedAuthority::new)
                            .toList()
            );
        }
        */

        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }
}
