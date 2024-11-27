//package com.tsb.technical.test.entities;
//
//import com.tsb.technical.test.entities.Account;
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Account fromAccount;
//
//    @ManyToOne
//    private Account toAccount;
//
//    private BigDecimal amount;
//    private LocalDateTime timestamp;
//    private String description;
//}
