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

    @GetMapping("/{accountId}")
    private ResponseEntity<List<Transaction>> findTransactionByAccountId(@PathVariable Long accountId) {
        // Try to find the appropriate item
        List<Transaction> transaction = transactionRepository.findAllByFromAccountId(accountId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    private ResponseEntity<Transaction> createAccount(
            @RequestBody Transaction newTransaction,
            UriComponentsBuilder ucb) {
        Transaction savedTransaction = this.transactionRepository.save(new Transaction(null, newTransaction.getFromAccountId(), newTransaction.getToAccountId(), newTransaction.getAmount()));
        URI locationOfNewTransaction = ucb
                .path("transaction/{transactionId}")
                .buildAndExpand(savedTransaction.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTransaction).build();
    }

}
