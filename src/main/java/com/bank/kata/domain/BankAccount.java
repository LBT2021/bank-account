package com.bank.kata.domain;

import java.time.Clock;
import java.time.Instant;

/**
 * @author lila.becha
 */
public class BankAccount {

    private final Statement statement = new Statement();
    private final Clock clock;
    private double balance = 0.0;

    public BankAccount(Clock clock) {
        super();
        this.clock = clock;
    }

    /**
     * deposit operation.
     *
     * @param amount
     */
    public void deposit(double amount) {
        updateBalanceDepositOperation(amount);
        updateTransactionHistory(TransactionType.DEPOSIT, clock.instant(), amount, balance);
    }

    /**
     * @return the account balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * @param amount
     */
    private void updateBalanceDepositOperation(double amount) {
        balance += Math.abs(amount);
    }

    /**
     * @param transactionType
     * @param transactionDate
     * @param amount
     * @param balance
     */
    private void updateTransactionHistory(TransactionType transactionType, Instant transactionDate, double amount, double balance) {
        statement.addTransaction(transactionType, transactionDate, amount, balance);
    }
}
