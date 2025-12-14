package com.springbaseproject.sharedstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application")
public record ApplicationProperties(
        String name
) {
}
