package com.springbaseproject.sharedstarter.security;

import com.springbaseproject.sharedstarter.services.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class JwtAuthoritiesConverter implements Converter<Jwt, AbstractAuthenticationToken>{

    private final JwtService jwtService;

    public JwtAuthoritiesConverter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Claims claims = jwtService.decode(jwt.getTokenValue());

        String username = claims.getSubject();

        List<String> roles = (List<String>) claims.get("roles", List.class);
        List<String> scopes = (List<String>) claims.get("scope", List.class);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (roles != null) {
            authorities.addAll(
                    roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList()
            );
        }

        if (scopes != null) {
            authorities.addAll(
                    scopes.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList()
            );
        }

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
