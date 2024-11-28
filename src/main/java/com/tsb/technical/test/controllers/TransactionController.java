package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.entities.Transaction;
import com.tsb.technical.test.repositories.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    private final TransactionRepository transactionRepository;

    private TransactionController(TransactionRepository accountRepository) {
        this.transactionRepository = accountRepository;
    };

    @GetMapping("/{transactionId}")
    private ResponseEntity<Transaction> findTransactionById(@PathVariable Long transactionId) {
        // Try to find the appropriate item
        Transaction transaction = transactionRepository.getById(transactionId);
        return ResponseEntity.ok(transaction);
    }

//    @PostMapping
//    private ResponseEntity<AccountHolder> createAccount(
//            @RequestBody Account newAccount,
//            UriComponentsBuilder ucb) {
//        Account savedAccount = this.transactionRepository.save(new Account(null, newAccount.getAccountHolderId(), newAccount.getBalance(), newAccount.getAccountNumber()));
//        URI locationOfNewAccountHolder = ucb
//                .path("account/{accountHolderId}")
//                .buildAndExpand(savedAccount.getId())
//                .toUri();
//        return ResponseEntity.created(locationOfNewAccountHolder).build();
//    }

}
