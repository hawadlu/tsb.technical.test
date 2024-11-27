package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
