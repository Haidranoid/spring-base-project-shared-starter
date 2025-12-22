package com.springbaseproject.sharedstarter.utils;

import com.springbaseproject.sharedstarter.entities.Account;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    @Nullable
    public Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (Account) authentication.getPrincipal();
    }
}
