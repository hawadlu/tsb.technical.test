package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import com.tsb.technical.test.repositories.AccountRepository;
import com.tsb.technical.test.security.AccountSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController
{
    private final AccountRepository accountRepository;
    private final AccountSecurity accountSecurity;


    private AccountController(AccountRepository accountRepository, AccountSecurity accountSecurity) {
        this.accountRepository = accountRepository;
        this.accountSecurity = accountSecurity;
    };

    @GetMapping("/{accountHolderId}")
    private ResponseEntity<List<Account>> findAccountHolderById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long accountHolderId){

        if (!accountSecurity.isAuthorized(authHeader, accountHolderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Account> accounts = accountRepository.findAllByAccountHolderId(accountHolderId);
        return ResponseEntity.ok(accounts);
    }
}
