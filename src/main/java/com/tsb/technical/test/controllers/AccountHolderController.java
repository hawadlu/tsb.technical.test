package com.tsb.technical.test.controllers;
import org.springframework.web.bind.annotation.*;


import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import com.tsb.technical.test.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accountHolder")
public class AccountHolderController
{
    private final AccountHolderRepository accountHolderRepository;

    private AccountHolderController(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    };

    @GetMapping("/{accountHolderId}")
    private ResponseEntity<AccountHolder> findAccountHolderById(@PathVariable Long accountHolderId) {
        // Try to find the appropriate item
        AccountHolder accountHolder = accountHolderRepository.findAccountHolderById(accountHolderId);
        if (accountHolder != null) return ResponseEntity.ok(accountHolder);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<AccountHolder> createAccountHolder(@RequestBody AccountHolder newAccountHolder, UriComponentsBuilder ucb) {
        AccountHolder savedAccountHolder = this.accountHolderRepository.save(new AccountHolder(null, newAccountHolder.getName(), newAccountHolder.getEmail()));
        URI locationOfNewAccountHolder = ucb
                .path("accountHolder/{accountHolderId}")
                .buildAndExpand(savedAccountHolder.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewAccountHolder).build();
    }
}
