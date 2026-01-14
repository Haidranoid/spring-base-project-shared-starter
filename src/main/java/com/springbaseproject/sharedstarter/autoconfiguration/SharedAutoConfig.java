package com.springbaseproject.sharedstarter.autoconfiguration;

import com.springbaseproject.sharedstarter.properties.ApplicationProperties;
import com.springbaseproject.sharedstarter.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class, ApplicationProperties.class})
public class SharedAutoConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

