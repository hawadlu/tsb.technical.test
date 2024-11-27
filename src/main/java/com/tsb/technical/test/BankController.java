package com.tsb.technical.test;

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
public class BankController
{
    private final AccountHolderRepository accountHolderRepository;
    private final AccountRepository accountRepository;

    private BankController(AccountHolderRepository accountHolderRepository, AccountRepository accountRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountRepository = accountRepository;
    };

    @GetMapping("/{accountHolderId}")
    private ResponseEntity<AccountHolder> findAccountHolderById(@PathVariable Long accountHolderId) {
        // Try to find the appropriate item
        AccountHolder accountHolder = accountHolderRepository.findAccountHolderById(accountHolderId);
        if (accountHolder != null) return ResponseEntity.ok(accountHolder);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{accountHolderId}/accounts")
    private ResponseEntity<Iterable<Account>> findAccountsByAccountHolderId(@PathVariable Long accountHolderId) {
       // Locate the appropriate account
        ResponseEntity<AccountHolder> accountHolder = findAccountHolderById(accountHolderId);
        if (accountHolder.getStatusCode() != HttpStatus.OK) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(this.accountRepository.findAllByAccountHolderId(accountHolderId));
    }

    @PostMapping
    private ResponseEntity<Void> createAccountHolder(@RequestBody AccountHolder newAccountHolder, UriComponentsBuilder uriComponentsBuilder) {
        AccountHolder savedAccountHolder = accountHolderRepository.save(new AccountHolder(null, newAccountHolder.getName(), newAccountHolder.getEmail()));

        URI newLocation = uriComponentsBuilder.path("accounts/{id}").buildAndExpand(savedAccountHolder.getId()).toUri();

        return ResponseEntity.created(newLocation).build();
    }
}
