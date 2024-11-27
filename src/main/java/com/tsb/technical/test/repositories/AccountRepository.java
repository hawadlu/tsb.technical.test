package com.tsb.technical.test.repositories;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Iterable<Account> findAllByAccountHolderId(Long id);
}
