package com.tsb.technical.test.entities;

import com.tsb.technical.test.entities.AccountHolder;
import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountHolderId;

    private Long accountNumber;
    private Long balance;
}
