package com.springbaseproject.sharedstarter.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record AccountDto(
        @Nullable Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        Roles role
) {
    @NonNull
    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[password]" + '\'' +
                ", role=" + role +
                '}';
    }
}