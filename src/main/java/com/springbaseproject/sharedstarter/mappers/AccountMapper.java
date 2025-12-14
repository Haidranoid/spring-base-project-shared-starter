package com.springbaseproject.sharedstarter.mappers;

import com.springbaseproject.sharedstarter.dtos.AccountDto;
import com.springbaseproject.sharedstarter.entities.Account;

public class AccountMapper implements Mapper<Account, AccountDto> {
    @Override
    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.id())
                .username(accountDto.username())
                .firstName(accountDto.firstName())
                .lastName(accountDto.lastName())
                .email(accountDto.email())
                .password(accountDto.password())
                .role(accountDto.role())
                .build();
    }

    @Override
    public AccountDto toDto(Account accountEntity) {
        return AccountDto.builder()
                .id(accountEntity.getId())
                .username(accountEntity.getUsername())
                .firstName(accountEntity.getFirstName())
                .lastName(accountEntity.getLastName())
                .email(accountEntity.getEmail())
                .password(accountEntity.getPassword())
                .role(accountEntity.getRole())
                .build();
    }
}