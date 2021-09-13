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
     * withdraw operation.
     *
     * @param amount
     */
    public void withdraw(double amount) {
        updateBalanceWithdrawalOperation(amount);
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
     * @param amount
     */
    private void updateBalanceWithdrawalOperation(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Transaction cancelled due to insufficient funds.");
        }
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
