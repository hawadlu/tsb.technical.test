package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/account")
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
    private ResponseEntity<Void> createCashCard(@RequestBody AccountHolder newAccountHolder, UriComponentsBuilder uriComponentsBuilder) {
        AccountHolder savedAccountHolder = accountHolderRepository.save(new AccountHolder(null, newAccountHolder.getName(), newAccountHolder.getEmail()));

        URI newLocation = uriComponentsBuilder.path("accounts/{id}").buildAndExpand(savedAccountHolder.getId()).toUri();

        return ResponseEntity.created(newLocation).build();
    }
}
