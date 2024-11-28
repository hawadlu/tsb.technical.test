package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.entities.Transaction;
import com.tsb.technical.test.repositories.AccountRepository;
import com.tsb.technical.test.repositories.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    };

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> findTransactionByAccountId(@PathVariable Long accountId) {
        // Try to find the appropriate item
        List<Transaction> transaction = transactionRepository.findAllByFromAccountId(accountId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody Transaction newTransaction,
            UriComponentsBuilder ucb) {

        // Find the accounts
        Account fromAccount = accountRepository.findAccountById(newTransaction.getFromAccountId());
        Account toAccount = accountRepository.findAccountById(newTransaction.getToAccountId());

        // Validate accounts exist
        if (fromAccount == null || toAccount == null) {
            return ResponseEntity.badRequest().build();
        }

        // Validate sufficient balance
        if (fromAccount.getBalance() < newTransaction.getAmount()) {
            return ResponseEntity.badRequest().build();
        }

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance() - newTransaction.getAmount());
        toAccount.setBalance(toAccount.getBalance() + newTransaction.getAmount());

        // Save updated accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create and save transaction
        Transaction savedTransaction = transactionRepository.save(new Transaction(
                null,
                newTransaction.getFromAccountId(),
                newTransaction.getToAccountId(),
                newTransaction.getAmount()
        ));

        URI locationOfNewTransaction = ucb
                .path("transaction/{transactionId}")
                .buildAndExpand(savedTransaction.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTransaction).build();
    }

}
