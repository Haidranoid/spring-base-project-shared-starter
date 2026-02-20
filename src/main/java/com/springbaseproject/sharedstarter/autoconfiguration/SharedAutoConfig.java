package com.springbaseproject.sharedstarter.autoconfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@RequiredArgsConstructor
public class SharedAutoConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
