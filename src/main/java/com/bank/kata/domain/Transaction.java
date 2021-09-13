package com.bank.kata.domain;

import java.time.Instant;

/**
 * @author lila.becha
 * @param transactionType
 * @param transactionDate
 * @param amount
 * @param balance
 */
public record Transaction(TransactionType transactionType, Instant transactionDate, double amount, double balance) {
}
