package com.tsb.technical.test.repositories;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByFromAccountId(Long id);
}
