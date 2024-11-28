package com.tsb.technical.test.controllers;
import com.tsb.technical.test.security.AccountAuthorization;
import com.tsb.technical.test.security.AccountSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/accountHolder")
public class AccountHolderController
{
    private final AccountHolderRepository accountHolderRepository;
    private final AccountSecurity accountSecurity;

    public AccountHolderController(
            AccountHolderRepository accountHolderRepository,
            AccountSecurity accountSecurity) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountSecurity = accountSecurity;
    }

    @GetMapping("/{accountHolderId}")
    @AccountAuthorization
    public ResponseEntity<AccountHolder> findAccountHolderById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long accountHolderId) {

        if (!accountSecurity.isAuthorized(authHeader, accountHolderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        AccountHolder accountHolder = accountHolderRepository.findAccountHolderById(accountHolderId);
        if (accountHolder != null) {
            return ResponseEntity.ok(accountHolder);
        }
        return ResponseEntity.notFound().build();
    }
}
