package com.tsb.technical.test.security;

import com.tsb.technical.test.security.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class AccountSecurity {
    private final JwtUtil jwtUtil;

    public AccountSecurity(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Long extractAccountHolderId(String authHeader) {
        String token = authHeader.substring(7);
        return jwtUtil.extractAccountHolderId(token);
    }

    public boolean isAuthorized(String authHeader, Long accountHolderId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        try {
            return extractAccountHolderId(authHeader).equals(accountHolderId);
        } catch (Exception e) {
            return false;
        }
    }
}
