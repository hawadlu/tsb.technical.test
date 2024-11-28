package com.tsb.technical.test.repositories;

import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAllByAccountHolderId(Long id);
}
