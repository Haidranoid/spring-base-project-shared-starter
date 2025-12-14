package com.springbaseproject.sharedstarter.autoconfiguration;

import com.springbaseproject.sharedstarter.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@RequiredArgsConstructor
public class ApplicationAutoConfig {

    @Bean
    public AccountMapper accountMapper() {
        return new AccountMapper();
    }
}
