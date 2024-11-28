package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import com.tsb.technical.test.repositories.AccountRepository;
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

    private AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    };

    @GetMapping("/{accountHolderId}")
    private ResponseEntity<List<Account>> findAccountHolderById(@PathVariable Long accountHolderId) {
        // Try to find the appropriate item
        List<Account> accounts = accountRepository.findAllByAccountHolderId(accountHolderId);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    private ResponseEntity<AccountHolder> createAccount(
            @RequestBody Account newAccount,
            UriComponentsBuilder ucb) {
        Account savedAccount = this.accountRepository.save(new Account(null, newAccount.getAccountHolderId(), newAccount.getBalance(), newAccount.getAccountNumber()));
        URI locationOfNewAccountHolder = ucb
                .path("account/{accountHolderId}")
                .buildAndExpand(savedAccount.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewAccountHolder).build();
    }

//    @PostMapping
//    private ResponseEntity<AccountHolder> createAccountHolder(@RequestBody AccountHolder newAccountHolder, UriComponentsBuilder ucb) {
//        AccountHolder savedAccountHolder = this.accountHolderRepository.save(new AccountHolder(null, newAccountHolder.getName(), newAccountHolder.getEmail()));
//        URI locationOfNewAccountHolder = ucb
//                .path("accountHolder/{accountHolderId}")
//                .buildAndExpand(savedAccountHolder.getId())
//                .toUri();
//        return ResponseEntity.created(locationOfNewAccountHolder).build();
//    }
}
