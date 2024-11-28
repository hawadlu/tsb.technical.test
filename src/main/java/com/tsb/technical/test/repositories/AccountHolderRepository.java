package com.tsb.technical.test.repositories;

import com.tsb.technical.test.entities.AccountHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface AccountHolderRepository extends CrudRepository<AccountHolder, Long> {
    AccountHolder findAccountHolderById(Long id);
}
