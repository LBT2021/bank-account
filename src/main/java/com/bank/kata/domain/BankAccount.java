package com.bank.kata.domain;

/**
 * @author lila.becha
 */
public class BankAccount {

    private double balance = 0.0;

    /**
     * deposit operation.
     *
     * @param amount
     */
    public void deposit(double amount) {
        updateBalanceDepositOperation(amount);
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
}
