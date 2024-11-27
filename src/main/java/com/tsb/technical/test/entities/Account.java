package com.tsb.technical.test.entities;

import com.tsb.technical.test.entities.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner;

    private String accountNumber;
    private String accountType; // e.g., CHECKING, SAVINGS
    private BigDecimal balance;
}
